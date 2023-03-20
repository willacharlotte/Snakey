package com.bbd.levelup2023.java.presentation.cmdline;

import com.bbd.levelup2023.java.domain.Board;
import com.bbd.levelup2023.java.domain.Food;
import com.bbd.levelup2023.java.domain.Snake;
import com.bbd.levelup2023.java.utils.ClearConsoleScreen;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.bbd.levelup2023.java.presentation.cmdline.Snakey.*;

public class Menu {
    Scanner myObj = new Scanner(System.in);  // Create a Scanner object
    //private final ClearConsoleScreen console = new ClearConsoleScreen();
    private Board my_board;
    private final List<Float> highScore = new ArrayList<>();

    private int get_board_size(){
        //Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        int board_size;
        System.out.println("===============================");
        System.out.println("Welcome to the Snakey: The Game");
        System.out.println("===============================");
        //System.out.println("Main menu");
        System.out.print("Enter a board size: ");
        board_size = this.myObj.nextInt();  // Read user input
        return board_size;
    }

    private void displayMenu(){
        System.out.println("Choose a movement: " +
                "UP = w, DOWN = s, LEFT = a, RIGHT = d");
    }

    public void newGame(){
        ClearConsoleScreen.clearConsole();
        int board_size = get_board_size();

        ClearConsoleScreen.clearConsole();
        this.my_board = createBoard(board_size);

        playGame();

        gameEnded();
    }

    private void playGame() {
        String userInput = "";
        int replaceFood = 0;

        Snake my_snake = this.my_board.getMy_snake();

        while (!userInput.equals("q") && my_snake.isAlive()){
            ClearConsoleScreen.clearConsole();
            System.out.println(this.my_board.isSnakeMax());

            replaceFood = addFoodToBoard(my_board, replaceFood);

            printGameDetails(my_board);

            userInput = getUserInput();

            moveSnake(my_board, userInput);

            System.out.println();
            System.out.println();

            replaceFood++;
        }
    }

    public Menu(){

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

    private String getUserInput() {
        displayMenu();
        System.out.print("User input: ");
        String userInput = myObj.nextLine();  // Read user input
        System.out.println(userInput);
        return userInput;
    }

    private static int addFoodToBoard(Board my_board, int replaceFood) {
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

        if (!my_board.getMy_snake().getFoodItems().isEmpty()) {
            System.out.println("Food items eaten: ");
            for (Food food : my_board.getMy_snake().getFoodItems()) {
                System.out.println("  " + food.getFoodName());
            }
        }

    }
}
