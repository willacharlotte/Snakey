package com.bbdsoftware.snakey.presentation.cmdline;

import com.bbdsoftware.snakey.domain.Board;
import com.bbdsoftware.snakey.domain.Food;
import com.bbdsoftware.snakey.domain.Snake;
import com.bbdsoftware.snakey.domain.User;
import com.bbdsoftware.snakey.utils.ClearConsoleScreen;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.bbdsoftware.snakey.presentation.cmdline.Snakey.*;

public class Menu {
    Scanner myObj = new Scanner(System.in);  // Create a Scanner object
    private Board myBoard;
    private final List<User> users = new ArrayList<>();

    private int get_board_size(){
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
        this.myBoard = createBoard(board_size);

        playGame();

        gameEnded(myObj);
    }

    private void playGame() {
        String userInput = "";
        int replaceFood = 0;

        Snake my_snake = this.myBoard.getMySnake();

        while (!userInput.equals("q") && my_snake.isAlive()){
            ClearConsoleScreen.clearConsole();
            System.out.println(this.myBoard.isSnakeMax());

            replaceFood = addFoodToBoard(myBoard, replaceFood);

            printGameDetails(myBoard);

            userInput = getUserInput();

            moveSnake(myBoard, userInput);

            System.out.println();
            System.out.println();

            replaceFood++;
        }
    }

    public Menu(){ }

    private void gameEnded(Scanner myObj){
        System.out.println("Game ended!");
        //this.highScore.add(this.my_board.getMy_snake().getScore());

        System.out.print("Enter your username: ");
        String username = myObj.nextLine();  // Read user input
        User new_user = new User(username, this.myBoard.getMySnake().getScore());
        this.users.add(new_user);

        //System.out.println("High Scores: " + this.highScore);
        System.out.println("Highscores: " + this.users);

        System.out.print("Would you like to play again? (Y=yes, N=no) ");
        String userInput = myObj.nextLine();  // Read user input
        if (userInput.equals("Y")){
            this.myBoard = null;
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
        else if (replaceFood == my_board.getBoardSize() - 1){
            replaceFood = -1;
        }
        return replaceFood;
    }

    private static void printGameDetails(Board my_board) {
        //System.out.println(my_board);
        my_board.printBoard();
        System.out.println("Score: " + my_board.getMySnake().getScore());

        if (!my_board.getMySnake().getFoodItems().isEmpty()) {
            System.out.println("Food items eaten: ");
            for (Food food : my_board.getMySnake().getFoodItems()) {
                System.out.println("  " + food.getFoodName());
            }
        }

    }
}
