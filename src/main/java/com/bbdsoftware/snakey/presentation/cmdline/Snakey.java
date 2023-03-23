package com.bbdsoftware.snakey.presentation.cmdline;

import com.bbdsoftware.snakey.controllers.BoardController;
import com.bbdsoftware.snakey.enums.Direction;

public class Snakey {
    /**
     * creates a board using the board size
     * @param boardSize the size of the board
     * @return the created Board
     */
    public static BoardController createBoard(int boardSize){
        return new BoardController(boardSize);
    }

    /**
     * adds food to the board
     * @param myBoard the board to add the food onto
     */
    public static void addFood(BoardController myBoard){
        myBoard.getFood();
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
    public static void processSnakeMovement(BoardController myBoard, Direction userDirection) {
        myBoard.processSnakeMovement(userDirection);
    }

    public static boolean validateUserInput(BoardController myBoard, Direction userInput){
        return myBoard.isValidMoveForSnake(userInput);
    }
}
