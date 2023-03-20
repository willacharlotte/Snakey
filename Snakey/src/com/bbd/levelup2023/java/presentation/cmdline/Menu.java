package com.bbd.levelup2023.java.presentation.cmdline;

import com.bbd.levelup2023.java.domain.Board;
import com.bbd.levelup2023.java.domain.Snake;
import com.bbd.levelup2023.java.utils.ClearConsoleScreen;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.bbd.levelup2023.java.presentation.cmdline.Snakey.*;

public class Menu {
    Scanner myObj = new Scanner(System.in);  // Create a Scanner object
    private final ClearConsoleScreen console = new ClearConsoleScreen();
    private Board my_board;
    private List<Float> highScore = new ArrayList<>();

    private int get_board_size(){
        //Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        int board_size = 0;
        System.out.println("Welcome to the Snakey: The Game");
        System.out.println("Main menu");
        System.out.print("Enter a board size: ");
        board_size = this.myObj.nextInt();  // Read user input
        return board_size;
    }

    public void newGame(){
        console.clearConsole();
        int board_size = get_board_size();

        console.clearConsole();
        this.my_board = createBoard(board_size);

        String userInput = "";
        int replaceFood = 0;

        String menu = "Snakey \n" +
                "UP = w, DOWN = s, LEFT = a, RIGHT = d";
        Snake my_snake = my_board.getMy_snake();

        while (!userInput.equals("q") && my_snake.isAlive()){
            console.clearConsole();

            replaceFood = addFoodToBoard(my_board, replaceFood);

            printGameDetails(my_board);

            userInput = getUserInput(menu);

            moveSnake(my_board, userInput);

            System.out.println();
            System.out.println();

            replaceFood++;
        }

        gameEnded();
    }

    public Menu(){
        newGame();
    }

    private void gameEnded(){
        System.out.println("Game ended!");
        this.highScore.add(this.my_board.getMy_snake().getScore());
        System.out.println("High Scores: " + this.highScore);
        System.out.print("Would you like to play again? (Y=yes, N=no) ");
        String userInput = myObj.nextLine();  // Read user input
        if (userInput.equals("Y")){
            this.my_board = null;
            newGame();
        }
    }

    private String getUserInput(String menu) {
        String userInput;
        System.out.println(menu);
        System.out.print("User input: ");
        userInput = myObj.nextLine();  // Read user input
        System.out.println(userInput);
        return userInput;
    }

    private static int addFoodToBoard(Board my_board, int replaceFood) {
        //System.out.println("replaceFood = " + replaceFood);
        //System.out.println("add food: ");
        if(replaceFood == 0)
            addFood(my_board);
        else if (replaceFood == my_board.getBoard_size() - 1){
            replaceFood = -1;
        }
        return replaceFood;
    }

    private static void printGameDetails(Board my_board) {
        //System.out.println(my_board);
        my_board.printBoard();
        System.out.println("Score: " + my_board.getMy_snake().getScore());
    }
}
