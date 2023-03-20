package com.bbd.levelup2023.java.app;

import com.bbd.levelup2023.java.snakey.Board;
import com.bbd.levelup2023.java.snakey.Direction;
import com.bbd.levelup2023.java.snakey.Snake;

import java.util.Arrays;
import java.util.Scanner;

public class AppRunner {
    public static void main(String[] args) {
        Board my_board = new Board(7);
        //System.out.println(my_board);

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String userInput = "";

        int replaceFood = 0;

        String menu = "Snakey \n" +
                "UP = w, DOWN = s, LEFT = a, RIGHT = d";
        Snake my_snake = my_board.getMy_snake();

        while (!userInput.equals("q") && my_snake.getIsAlive()){
            //System.out.println("replaceFood = " + replaceFood);
            //System.out.println("add food: ");
            if(replaceFood == 0)
                my_board.addFood();
            else if (replaceFood == my_board.getBoard_size() - 1){
                replaceFood = -1;
            }
            //System.out.println(my_board);
            my_board.printBoard();

            System.out.println(menu);
            System.out.print("User input: ");
            userInput = myObj.nextLine();  // Read user input

            System.out.println(userInput);

            /* move snake */
            switch (userInput) {
                case "w" -> my_board.moveSnake(Direction.UP);
                case "s" -> my_board.moveSnake(Direction.DOWN);
                case "a" -> my_board.moveSnake(Direction.LEFT);
                case "d" -> my_board.moveSnake(Direction.RIGHT);
                default -> System.out.println("choose valid number");
            }

            System.out.println();
            System.out.println();

            replaceFood++;
        }

        System.out.println("Game ended!");
    }

}
