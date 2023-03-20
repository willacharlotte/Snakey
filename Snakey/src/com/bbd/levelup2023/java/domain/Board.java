package com.bbd.levelup2023.java.domain;

import com.bbd.levelup2023.java.enums.CellType;
import com.bbd.levelup2023.java.enums.Direction;
import com.bbd.levelup2023.java.enums.FoodTypes;

import java.util.Random;

public class Board {
    private int board_size;
    private Cell[][] board_map;
    private final Snake my_snake = new Snake();
    private Food my_food;

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
        return checkCell.getX() < this.board_size && checkCell.getY() < this.board_size &&
                checkCell.getX() >= 0 && checkCell.getY() >= 0;
    }

    /**
     * is a given Cell on the snake
     * @param checkCell the cell to check
     * @return if on the snake, return True
     */
    private boolean isOnSnake(Cell checkCell){
        for (Cell snake_cell : my_snake.getSnakeBlocks()){
            if (snake_cell.getX() == checkCell.getX() && snake_cell.getY() == checkCell.getY())
                return true;
        }
        return false;
    }

    /**
     * remove snake off the board
     */
    private void removeSnakeOffBoard(){
        for (int i=0; i<this.board_size ; i++){
            for (int j=0; j<this.board_size ; j++){
                if(this.board_map[i][j].getCellType().equals(CellType.SNAKE)){
                    this.board_map[i][j].setCellType(CellType.NONE);
                }
            }
        }
    }

    /**
     * add snake back on the board
     */
    private void addSnakeToBoard(){
        for(Cell snake_cell : my_snake.getSnakeBlocks()){
            this.board_map[snake_cell.getY()][snake_cell.getX()].setCellType(CellType.SNAKE);
        }
    }

    /**
     * move the snake on the board using the direction provided by the player
     * @param direction the direction to move the snake
     */
    public void moveSnake(Direction direction){
        int new_y = my_snake.getHead().getY();
        int new_x = my_snake.getHead().getX();

        Cell old_cell = this.board_map[new_y][new_x];
        removeSnakeOffBoard();

        if (direction.equals(Direction.UP)){
            new_y -= 1;
        }
        else if (direction.equals(Direction.DOWN)){
            new_y += 1;
        }
        else if (direction.equals(Direction.LEFT)){
            new_x -= 1;
        }
        else{
            new_x += 1;
        }

        Cell checkCell = new Cell(new_x, new_y);
        if (isValid(checkCell) && !my_snake.containsCell(checkCell)){
            Cell new_head = this.board_map[new_y][new_x];

            // if blank cell
            if (new_head.getCellType().equals(CellType.NONE)) {
                my_snake.moveSnake(new_head, direction);
            }

            // if contains food
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

    /**
     * add food to the board
     */
    public void addFood(){
        if (this.my_food == null) {
            Random rand = new Random();
            int int_x = rand.nextInt(this.board_size);
            int int_y = rand.nextInt(this.board_size);

            Cell food_cell = new Cell(int_x, int_y);

            // if not on snake
            while (isOnSnake(food_cell)) {
                int_x = rand.nextInt(this.board_size);
                int_y = rand.nextInt(this.board_size);
                food_cell = new Cell(int_x, int_y);
            }

            int foodType = rand.nextInt(FoodTypes.values().length);

            if (FoodTypes.values()[foodType].equals(FoodTypes.APPLE)) {
                this.my_food = new Apple(food_cell);
                this.board_map[int_y][int_x].setCellType(CellType.FOOD);
            }
            else if (FoodTypes.values()[foodType].equals(FoodTypes.ORANGE)){
                this.my_food = new Orange(food_cell);
                this.board_map[int_y][int_x].setCellType(CellType.FOOD);
            }
            //System.out.println("Food added to board: " + this.my_food);
        }
    }

    /**
     * printing the board on the cmd line
     */
    public void printBoard(){
        for (int i=0; i<this.board_size ; i++){
            for (int j=0; j<this.board_size ; j++){
                Cell this_cell = this.board_map[i][j];
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

    public boolean isSnakeMax(){
        return this.my_snake.getSnakeBlocks().size() == this.board_size * this.board_size;
    }

    public int getBoard_size() {
        return board_size;
    }

    public Snake getMy_snake() {
        return my_snake;
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
