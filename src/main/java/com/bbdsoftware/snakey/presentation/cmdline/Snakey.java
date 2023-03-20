package com.bbdsoftware.snakey.presentation.cmdline;

import com.bbdsoftware.snakey.domain.Board;
import com.bbdsoftware.snakey.domain.Snake;
import com.bbdsoftware.snakey.enums.Direction;

import java.util.Scanner;

public class Snakey {
    /**
     * creates a board using the board size
     * @param board_size the size of the board
     * @return the created Board
     */
    public static Board createBoard(int board_size){
        return new Board(board_size);
    }

    /**
     * adds food to the board
     * @param my_board the board to add the food onto
     */
    public static void addFood(Board my_board){
        my_board.addFood();
    }

    /**
     * moves the snake on the board
     * @param my_board the board the snake is on
     * @param userInput the users input
     */
    public static void moveSnake(Board my_board, String userInput) {
        /* move snake */
        switch (userInput) {
            case "w" -> my_board.moveSnake(Direction.UP);
            case "s" -> my_board.moveSnake(Direction.DOWN);
            case "a" -> my_board.moveSnake(Direction.LEFT);
            case "d" -> my_board.moveSnake(Direction.RIGHT);
            default -> System.out.println("choose valid number");
        }
    }
}