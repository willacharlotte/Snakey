package com.bbd.levelup2023.java.snakey;

import java.util.Arrays;
import java.util.Random;

public class Board {
    private int board_size = 11;
    private Cell[][] board_map;
    private final Snake my_snake = new Snake();

    private Food my_food;

    public Board(){
        System.out.println("new board");
        createBoard();
        createSnake();
    }

    public Board(int board_size) throws IllegalArgumentException{
        if(board_size % 2 == 0){
            throw new IllegalArgumentException("Board size should be odd number!");
        }

        this.board_size = board_size;
        createBoard();
        createSnake();
    }

    /**
     * creates the board using the board_size
     */
    private void createBoard(){
        this.board_map = new Cell[board_size][board_size];
        createCells();
    }

    /**
     * converts each element in the board_map to a Cell object
     */
    private void createCells(){
        for (int i=0; i<this.board_size ; i++){
            for (int j=0; j<this.board_size ; j++){
                this.board_map[i][j] = new Cell(j, i);
            }
        }
    }

    /**
     * creates a snake in the center left of the board
     */
    private void createSnake(){
        Cell center_cell = this.board_map[this.board_size/2][0];
        center_cell.setCellType(CellType.SNAKE);
        my_snake.setHead(center_cell);
    }

    /**
     * checks in the new cell is within the board
     * @param checkCell the new cell the snake will move to
     * @return if the cell is valid or not
     */
    private boolean isValid(Cell checkCell){
        System.out.println(checkCell.getX() + "," + checkCell.getY() + " \t " + (this.board_size -1));
        return checkCell.getX() < this.board_size && checkCell.getY() < this.board_size &&
                checkCell.getX() >= 0 && checkCell.getY() >= 0;
    }

    private boolean isOnSnake(Cell checkCell){
        for (Cell snake_cell : my_snake.getSnakeBlocks()){
            if (snake_cell.getX() == checkCell.getX() && snake_cell.getY() == checkCell.getY())
                return true;
        }
        return false;
    }

    private void removeSnakeOffBoard(){
        for (int i=0; i<this.board_size ; i++){
            for (int j=0; j<this.board_size ; j++){
                if(this.board_map[i][j].getCellType().equals(CellType.SNAKE)){
                    //System.out.println("before: " + this.board_map[i][j]);
                    this.board_map[i][j].setCellType(CellType.NONE);
                    //System.out.println("after: " + this.board_map[i][j]);
                }
            }
        }
    }

    private void addSnakeToBoard(){
        for(Cell snake_cell : my_snake.getSnakeBlocks()){
            this.board_map[snake_cell.getY()][snake_cell.getX()].setCellType(CellType.SNAKE);
        }
    }

    public void moveSnake(Direction direction){
        int new_y = my_snake.getHead().getY();
        int new_x = my_snake.getHead().getX();

        Cell old_cell = this.board_map[new_y][new_x];
        removeSnakeOffBoard();

        if (direction.equals(Direction.UP)){
            System.out.println("moving up");
            new_y -= 1;
        }
        else if (direction.equals(Direction.DOWN)){
            System.out.println("moving down");
            new_y += 1;
        }
        else if (direction.equals(Direction.LEFT)){
            System.out.println("moving left");
            new_x -= 1;
        }
        else{
            System.out.println("moving right");
            new_x += 1;
        }

        Cell checkCell = new Cell(new_x, new_y);
        if (isValid(checkCell) && !my_snake.containsCell(checkCell)){
            Cell new_head = this.board_map[new_y][new_x];
            System.out.println("new_head: " + new_head);
            if (new_head.getCellType().equals(CellType.NONE)) {
                my_snake.moveSnake(new_head, direction);
            }
            else if (new_head.getCellType().equals(CellType.FOOD)){
                my_snake.moveSnake(new_head, direction, my_food);
                this.my_food = null;
            }
            addSnakeToBoard();
        }
        else{
            old_cell.setCellType(CellType.SNAKE);
            my_snake.killSnake();
        }
    }

    public Snake getMy_snake() {
        return my_snake;
    }

    public void addFood(){
        Random rand = new Random();
        int int_x = rand.nextInt(this.board_size);
        int int_y = rand.nextInt(this.board_size);
        //int int_x = 0, int_y = 3;

        Cell food_cell = new Cell(int_x, int_y);

        // if not on snake
        while (isOnSnake(food_cell)){
            System.out.println("on snake");
            int_x = rand.nextInt(this.board_size);
            int_y = rand.nextInt(this.board_size);
            food_cell = new Cell(int_x, int_y);
        }

        int foodType = rand.nextInt(FoodTypes.values().length);
        //System.out.println(int_x + ", " + int_y + "-->" + foodType);
        //System.out.println("Food: " + FoodTypes.values()[foodType]);

        if (this.my_food != null){
            this.board_map[int_y][int_x].setCellType(CellType.NONE);
            this.my_food = null;
        }

        if(FoodTypes.values()[foodType].equals(FoodTypes.APPLE)){
            Apple my_apple = new Apple(food_cell);
            //System.out.println("Apple: " + my_apple);
            this.my_food = my_apple;
            this.board_map[int_y][int_x].setCellType(CellType.FOOD);
            System.out.println("Food added to board: " + this.my_food);
        }
    }

    public void printBoard(){
        for (int i=0; i<this.board_size ; i++){
            for (int j=0; j<this.board_size ; j++){
                Cell this_cell = this.board_map[i][j];
                //System.out.print(this.board_map[i][j] + " |");
                if (this_cell.getCellType().equals(CellType.NONE)){
                    System.out.print('N');
                }
                else if (this_cell.getCellType().equals(CellType.SNAKE)){
                    System.out.print('S');
                }
                else if (this_cell.getCellType().equals(CellType.FOOD)) {
                    System.out.print('F');
                }
                else {
                    System.out.print('?');
                }
                System.out.print(" | ");
            }
            System.out.println();
        }
    }

    public int getBoard_size() {
        return board_size;
    }

    @Override
    public String toString() {
        StringBuilder board_map_toString = new StringBuilder();
        for (int i=0; i<this.board_size ; i++){
            for (int j=0; j<this.board_size ; j++){
                board_map_toString.append(this.board_map[i][j]).append(" |");
            }
            board_map_toString.append("===\n");
        }

        return "Board{" +
                "board_size=" + board_size +
                ", \nboard_map=" + board_map_toString +
                ", \nmy_snake=" + my_snake +
                ", \nmy_food=" + my_food +
                '}';
    }
}
