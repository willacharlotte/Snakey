package com.bbdsoftware.snakey.presentation.cmdline;

import com.bbdsoftware.snakey.domain.Board;
import com.bbdsoftware.snakey.domain.Food;
import com.bbdsoftware.snakey.domain.Snake;
import com.bbdsoftware.snakey.domain.User;
import com.bbdsoftware.snakey.enums.Direction;
import com.bbdsoftware.snakey.utils.ClearConsoleScreen;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.bbdsoftware.snakey.presentation.cmdline.Snakey.*;

public class Menu {
    Scanner myObj = new Scanner(System.in);  // Create a Scanner object
    private Board myBoard;
    private final List<User> users = new ArrayList<>();

    public Menu(){ }

    public void newGame(){
        ClearConsoleScreen.clearConsole();
        displayWelcomeScreen();
        int board_size = get_board_size();

        ClearConsoleScreen.clearConsole();
        this.myBoard = createBoard(board_size);

        playGame();
    }

    /**
     * displays the welcome screen
     */
    private void displayWelcomeScreen(){
        System.out.println("===============================");
        System.out.println("Welcome to the Snakey: The Game");
        System.out.println("===============================");
    }

    /**
     * gets boardSize from the user
     * @return the boardSize
     */
    private int get_board_size(){
        System.out.print("Enter a board size: ");
        return this.myObj.nextInt();
    }

    /**
     * plays the game
     */
    private void playGame() {
        String userInput = "";
        int replaceFood = 0;
        String error = "";

        Snake my_snake = this.myBoard.getMySnake();

        while (my_snake.isAlive()){
            ClearConsoleScreen.clearConsole();
            if (!error.isEmpty()){
                System.out.println(error);
                error = "";
            }
            replaceFood = addFoodToBoard(replaceFood);

            displayGameDetails();

            userInput = getUserInput();
            if (userInput.equals("q"))
                displayGameQuit();

            Direction userDirection = convertUserInput(userInput);
            if (userDirection == null) {
                error = "Choose a valid movement";
                continue;
            }

            if (!validateUserInput(this.myBoard, userDirection)) {
                error = "Choose a movement to turn into a right angle";
                continue;
            }
            moveSnakeOnBoard(this.myBoard, userDirection);

            System.out.println();
            System.out.println();

            if (this.myBoard.isSnakeMax())
                displayGameWon();

            replaceFood++;
        }
        displayGameLost();
    }

    /**
     * add food to the board
     * @param replaceFood determines if the food should be placed or not
     * @return the replaceFood
     */
    private int addFoodToBoard(int replaceFood) {
        // if replaceFood is 0
        if(replaceFood == 0)
            addFood(this.myBoard);

            // reset replaceFood if its the size of the board
        else if (replaceFood == this.myBoard.getBoardSize() - 1)
            replaceFood = -1;

        return replaceFood;
    }

    /**
     * display the game details
     */
    private void displayGameDetails() {
        this.myBoard.printBoard();
        System.out.println("Score: " + this.myBoard.getMySnake().getScore());

        if (!this.myBoard.getMySnake().getFoodItems().isEmpty()) {
            System.out.println("Food items eaten: ");
            for (Food food : myBoard.getMySnake().getFoodItems()) {
                System.out.println("  " + food.getFoodName());
            }
        }
    }

    /**
     * get users input for movement
     * @return the movement chosen
     */
    private String getUserInput() {
        displayMovementMenu();
        System.out.print("User input: ");
        return myObj.nextLine();
    }

    /**
     * displays the movement menu
     */
    private void displayMovementMenu(){
        System.out.println("Choose a movement: " +
                "UP = w, DOWN = s, LEFT = a, RIGHT = d, QUIT = q");
    }

    /**
     * display game won
     */
    private void displayGameWon(){
        System.out.println("You won!");
        displayGameEnded();
    }

    /**
     * display game lost
     */
    private void displayGameLost(){
        System.out.println("You lost!");
        displayGameEnded();
    }

    /**
     * display game quit
     */
    private void displayGameQuit(){
        System.out.println("You quit!");
        displayGameEnded();
    }

    /**
     * display game ended
     */
    private void displayGameEnded() {
        System.out.print("Enter your username: ");
        String username = myObj.nextLine();  // Read user input
        User new_user = new User(username, this.myBoard.getMySnake().getScore());
        this.users.add(new_user);

        System.out.println("High scores: " + this.users);

        System.out.print("Would you like to play again? (Y=yes, N=no) ");
        String userInput = myObj.nextLine();  // Read user input
        if (userInput.equals("Y")){
            this.myBoard = null;
            newGame();
        }
    }
}
