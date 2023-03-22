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
                this.boardMap[i][j] = new Cell(j, i, Direction.RIGHT);
            }
        }
    }

    /**
     * creates a snake in the center left of the board
     */
    private void createSnake(){
        Cell centerCell = this.boardMap[this.boardSize /2][0];
        centerCell.setCellType(CellType.SNAKE);
        mySnake.setHead(centerCell);
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
        for (Cell snakeCell : mySnake.getSnakeBlocks()){
            if (snakeCell.getX() == checkCell.getX() && snakeCell.getY() == checkCell.getY())
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
        for(Cell snakeCell : mySnake.getSnakeBlocks()){
            this.boardMap[snakeCell.getY()][snakeCell.getX()].setCellType(CellType.SNAKE);
        }
    }

    public boolean isValidMoveForSnake(Direction snakeNewDirection){
        return this.mySnake.isValidTurn(snakeNewDirection);
    }

    /**
     * move the snake on the board using the direction provided by the player
     * @param direction the direction to move the snake
     */
    public void processSnakeMovement(Direction direction){
        Cell newCell = this.mySnake.moveSnake(direction);

        if (isValid(newCell)){
            Cell newHead = this.boardMap[newCell.getY()][newCell.getX()];
            newHead.setCellDirection(direction);

            // if blank cell
            if (newHead.getCellType().equals(CellType.NONE)) {
                mySnake.moveSnake(newHead);
            }

            // if contains food
            else if (newHead.getCellType().equals(CellType.FOOD)){
                mySnake.moveSnake(newHead, this.myFood);
                this.myFood = null;
            }

            // if contains snake
            else if (newHead.getCellType().equals(CellType.SNAKE)){
                mySnake.killSnake();
            }

            removeSnakeOffBoard();
            addSnakeToBoard();
        }
        else{
            mySnake.killSnake();
        }
    }

    /**
     * add food to the board
     */
    public Food getFood(){
        if (this.myFood == null) {
            Random rand = new Random();
            int int_x = rand.nextInt(this.boardSize);
            int int_y = rand.nextInt(this.boardSize);

            Cell foodCell = new Cell(int_x, int_y, Direction.UP);

            // if not on snake
            while (isOnSnake(foodCell)) {
                int_x = rand.nextInt(this.boardSize);
                int_y = rand.nextInt(this.boardSize);
                foodCell = new Cell(int_x, int_y, Direction.UP);
            }

            int foodType = rand.nextInt(FoodTypes.values().length);
            switch(foodType){
                case 0:
                    this.myFood = new Apple(foodCell);
                    this.boardMap[int_y][int_x].setCellType(CellType.FOOD);
                    break;
                case 1:
                    this.myFood = new Orange(foodCell);
                    this.boardMap[int_y][int_x].setCellType(CellType.FOOD);
                    break;
                case 2:
                    this.myFood = new Banana(foodCell);
                    this.boardMap[int_y][int_x].setCellType(CellType.FOOD);
                    break;
            }
        }
        return this.myFood;
    }

    /**
     * printing the board on the cmd line
     */
    public void printBoard(){
        for (int i = 0; i<this.boardSize; i++){
            for (int j = 0; j<this.boardSize; j++){
                Cell thisCell = this.boardMap[i][j];
                if (thisCell.getCellType().equals(CellType.NONE)){
                    System.out.print('N');
                }
                else if (thisCell.getCellType().equals(CellType.SNAKE)){
                    System.out.print('S');
                }
                else if (thisCell.getCellType().equals(CellType.FOOD)) {
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
        StringBuilder boardMapToString = new StringBuilder();
        for (int i = 0; i<this.boardSize; i++){
            for (int j = 0; j<this.boardSize; j++){
                boardMapToString.append(this.boardMap[i][j]).append(" |");
            }
            boardMapToString.append("===\n");
        }

        return "Board{" +
                "boardSize=" + boardSize +
                ", \nboardMap=" + boardMapToString +
                ", \nmySnake=" + mySnake +
                ", \nmyFood=" + myFood +
                '}';
    }
}
