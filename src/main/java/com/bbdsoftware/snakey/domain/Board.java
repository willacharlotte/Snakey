package com.bbdsoftware.snakey.domain;

import com.bbdsoftware.snakey.enums.CellType;
import com.bbdsoftware.snakey.enums.Direction;
import com.bbdsoftware.snakey.enums.FoodTypes;

import java.util.Random;

public class Board {
    private final int boardSize;
    private Cell[][] boardMap;
    private final Snake mySnake = new Snake();
    private Food myFood;

    public Board(int boardSize) throws IllegalArgumentException{
        if(boardSize % 2 == 0){
            throw new IllegalArgumentException("Board size should be odd number!");
        }

        this.boardSize = boardSize;
        createBoard();
        createSnake();
    }

    /**
     * creates the board using the board_size
     */
    private void createBoard(){
        this.boardMap = new Cell[boardSize][boardSize];
        createCells();
    }

    /**
     * converts each element in the board_map to a Cell object
     */
    private void createCells(){
        for (int i = 0; i<this.boardSize; i++){
            for (int j = 0; j<this.boardSize; j++){
                this.boardMap[i][j] = new Cell(j, i);
            }
        }
    }

    /**
     * creates a snake in the center left of the board
     */
    private void createSnake(){
        Cell center_cell = this.boardMap[this.boardSize /2][0];
        center_cell.setCellType(CellType.SNAKE);
        mySnake.setHead(center_cell);
    }

    /**
     * checks in the new cell is within the board
     * @param checkCell the new cell the snake will move to
     * @return if the cell is valid or not
     */
    private boolean isValid(Cell checkCell){
        return checkCell.getX() < this.boardSize && checkCell.getY() < this.boardSize &&
                checkCell.getX() >= 0 && checkCell.getY() >= 0;
    }

    /**
     * is a given Cell on the snake
     * @param checkCell the cell to check
     * @return if on the snake, return True
     */
    private boolean isOnSnake(Cell checkCell){
        for (Cell snake_cell : mySnake.getSnakeBlocks()){
            if (snake_cell.getX() == checkCell.getX() && snake_cell.getY() == checkCell.getY())
                return true;
        }
        return false;
    }

    /**
     * remove snake off the board
     */
    private void removeSnakeOffBoard(){
        for (int i = 0; i<this.boardSize; i++){
            for (int j = 0; j<this.boardSize; j++){
                if(this.boardMap[i][j].getCellType().equals(CellType.SNAKE)){
                    this.boardMap[i][j].setCellType(CellType.NONE);
                }
            }
        }
    }

    /**
     * add snake back on the board
     */
    private void addSnakeToBoard(){
        for(Cell snake_cell : mySnake.getSnakeBlocks()){
            this.boardMap[snake_cell.getY()][snake_cell.getX()].setCellType(CellType.SNAKE);
        }
    }

    /**
     * move the snake on the board using the direction provided by the player
     * @param direction the direction to move the snake
     */
    public void moveSnake(Direction direction){
        int new_y = mySnake.getHead().getY();
        int new_x = mySnake.getHead().getX();

        Cell old_cell = this.boardMap[new_y][new_x];
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
        if (isValid(checkCell) && !mySnake.containsCell(checkCell)){
            Cell new_head = this.boardMap[new_y][new_x];

            // if blank cell
            if (new_head.getCellType().equals(CellType.NONE)) {
                mySnake.moveSnake(new_head, direction);
            }

            // if contains food
            else if (new_head.getCellType().equals(CellType.FOOD)){
                mySnake.moveSnake(new_head, direction, myFood);
                this.myFood = null;
            }
            addSnakeToBoard();
        }
        else{
            old_cell.setCellType(CellType.SNAKE);
            mySnake.killSnake();
        }
    }

    /**
     * add food to the board
     */
    public void addFood(){
        if (this.myFood == null) {
            Random rand = new Random();
            int int_x = rand.nextInt(this.boardSize);
            int int_y = rand.nextInt(this.boardSize);

            Cell food_cell = new Cell(int_x, int_y);

            // if not on snake
            while (isOnSnake(food_cell)) {
                int_x = rand.nextInt(this.boardSize);
                int_y = rand.nextInt(this.boardSize);
                food_cell = new Cell(int_x, int_y);
            }

            int foodType = rand.nextInt(FoodTypes.values().length);

            if (FoodTypes.values()[foodType].equals(FoodTypes.APPLE)) {
                this.myFood = new Apple(food_cell);
                this.boardMap[int_y][int_x].setCellType(CellType.FOOD);
            }
            else if (FoodTypes.values()[foodType].equals(FoodTypes.ORANGE)){
                this.myFood = new Orange(food_cell);
                this.boardMap[int_y][int_x].setCellType(CellType.FOOD);
            }
            //System.out.println("Food added to board: " + this.my_food);
        }
    }

    /**
     * printing the board on the cmd line
     */
    public void printBoard(){
        for (int i = 0; i<this.boardSize; i++){
            for (int j = 0; j<this.boardSize; j++){
                Cell this_cell = this.boardMap[i][j];
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
        return this.mySnake.getSnakeBlocks().size() == this.boardSize * this.boardSize;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public Snake getMySnake() {
        return mySnake;
    }

    @Override
    public String toString() {
        StringBuilder board_map_toString = new StringBuilder();
        for (int i = 0; i<this.boardSize; i++){
            for (int j = 0; j<this.boardSize; j++){
                board_map_toString.append(this.boardMap[i][j]).append(" |");
            }
            board_map_toString.append("===\n");
        }

        return "Board{" +
                "boardSize=" + boardSize +
                ", \nboardMap=" + board_map_toString +
                ", \nmySnake=" + mySnake +
                ", \nmyFood=" + myFood +
                '}';
    }
}
