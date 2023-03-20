package com.bbd.levelup2023.java.presentation.cmdline;

import com.bbd.levelup2023.java.domain.Board;
import com.bbd.levelup2023.java.domain.Snake;
import com.bbd.levelup2023.java.enums.Direction;

import java.util.Scanner;

public class Snakey {
    public static Board createBoard(int board_size){
        Board my_board = new Board(board_size);
        return my_board;
    }

    public static void addFood(Board my_board){
        my_board.addFood();
    }

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
