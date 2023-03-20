package com.bbd.levelup2023.java.snakey;


import java.util.Arrays;

public class Board {
    private int board_size = 11;
    private Cell[][] board_map;
    private final Snake my_snake = new Snake();

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

    private void removeSnakeOffBoard(){
        for (int i=0; i<this.board_size ; i++){
            for (int j=0; j<this.board_size ; j++){
                if(this.board_map[i][j].getCellType().equals(CellType.SNAKE)){
                    System.out.println("before: " + this.board_map[i][j]);
                    this.board_map[i][j].setCellType(CellType.NONE);
                    System.out.println("after: " + this.board_map[i][j]);
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
        System.out.println(this);

        //old_cell.setCellType(CellType.NONE);

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
        if (isValid(checkCell)){
            Cell new_head = this.board_map[new_y][new_x];
            new_head.setCellType(CellType.SNAKE);

            // System.out.println("new head: " + new_head);
            // my_snake.setHead(new_head);
            my_snake.moveSnake(new_head, direction);
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
                ",\n board_map=\n" + board_map_toString +
                ",\n my_snake=" + my_snake +
                '}';
    }
}
