package com.bbdsoftware.snakey.presentation.cmdline;

import com.bbdsoftware.snakey.domain.Board;
import com.bbdsoftware.snakey.enums.Direction;

public class Snakey {
    /**
     * creates a board using the board size
     * @param boardSize the size of the board
     * @return the created Board
     */
    public static Board createBoard(int boardSize){
        return new Board(boardSize);
    }

    /**
     * adds food to the board
     * @param myBoard the board to add the food onto
     */
    public static void addFood(Board myBoard){
        myBoard.addFood();
    }

    /**
     * converts the user input to a valid movement
     * @param userInput the
     * @return Direction chosen by user
     */
    public static Direction convertUserInput(String userInput) {
        switch (userInput) {
            case "w" -> {
                return Direction.UP;
            }
            case "s" -> {
                return Direction.DOWN;
            }
            case "a" -> {
                return Direction.LEFT;
            }
            case "d" -> {
                return Direction.RIGHT;
            }
            default -> {
                return null;
            }
        }
    }

    /**
     * moves the snake on the board
     * @param myBoard the board the snake is on
     * @param userDirection the users input
     */
    public static void moveSnake(Board myBoard, Direction userDirection) {
        myBoard.moveSnake(userDirection);
    }

    public static boolean validateUserInput(Board myBoard, Direction userInput){
        return myBoard.isValidMoveForSnake(userInput);
    }
}
