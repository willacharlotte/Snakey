package com.bbdsoftware.snakey;

import com.bbdsoftware.snakey.controllers.MovementController;
import com.bbdsoftware.snakey.domain.Cell;
import com.bbdsoftware.snakey.domain.Board;
import com.bbdsoftware.snakey.domain.Food;
import com.bbdsoftware.snakey.domain.Snake;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GameController extends JPanel implements ActionListener {

    static final int numBlocks = 15;
    static final int blockUnit = 50;
    static final int snakeUnit = 50;
    static final int screenWidth = numBlocks*blockUnit;
    static final int screenHeight = numBlocks*blockUnit;
    
    Random random = new Random();
    Board board = new Board(numBlocks);
    Snake snake;
    Food foodItem;
    int foodX, foodY;

    int speed = 350;
    boolean moving = false;
    Timer timer = new Timer(speed, this);
    BufferedImage snakeSegmentHorizontal, snakeSegmentVertical;
    BufferedImage snakeHeadUp, snakeHeadDown, snakeHeadLeft, snakeHeadRight;
    BufferedImage snakeTailUp, snakeTailDown, snakeTailLeft, snakeTailRight;
    BufferedImage apple, banana, pear, orange;

    GameController() {
        snake = board.getMySnake();
        this.setBackground(Color.gray);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setFocusable(true);
        this.addKeyListener(new MovementController(snake));
        foodItem = board.getFood();
        foodX = foodItem.getFoodCell().getX();
        foodY = foodItem.getFoodCell().getY();

        loadImages();
        timer.start();
        startGame();
    }

    private void startGame() {
        moving = true;
    }

    private void loadImages(){
        try {
            snakeSegmentHorizontal = ImageIO.read(new File("src/main/java/assets/snakeSegmentHorizontal.png"));
            snakeSegmentVertical = ImageIO.read(new File("src/main/java/assets/snakeSegmentVertical.png"));
            
            snakeHeadUp = ImageIO.read(new File("src/main/java/assets/snakeHeadUp.png"));
            snakeHeadDown = ImageIO.read(new File("src/main/java/assets/snakeHeadDown.png"));
            snakeHeadLeft = ImageIO.read(new File("src/main/java/assets/snakeHeadLeft.png"));
            snakeHeadRight = ImageIO.read(new File("src/main/java/assets/snakeHeadRight.png"));
            
            snakeTailUp = ImageIO.read(new File("src/main/java/assets/snakeTailUp.png"));
            snakeTailDown = ImageIO.read(new File("src/main/java/assets/snakeTailDown.png"));
            snakeTailLeft = ImageIO.read(new File("src/main/java/assets/snakeTailLeft.png"));
            snakeTailRight = ImageIO.read(new File("src/main/java/assets/snakeTailRight.png"));

            apple = ImageIO.read(new File("src/main/java/assets/apple.png"));
            banana = ImageIO.read(new File("src/main/java/assets/banana.png"));
            orange = ImageIO.read(new File("src/main/java/assets/orange.png"));
            pear = ImageIO.read(new File("src/main/java/assets/pear.png"));
            
        } catch (IOException e) {
            // Add error handing here
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        int snakeCount = 0;
        for(Cell s : snake.getSnakeBlocks()){
            if(snakeCount == snake.getCurrentLength()-1){
                switch(s.getCellDirection()){
                    case DOWN:
                        g.drawImage(snakeHeadDown, s.getX()*blockUnit, s.getY()*blockUnit, snakeUnit, snakeUnit, null);         
                        break;
                    case UP:
                        g.drawImage(snakeHeadUp, s.getX()*blockUnit, s.getY()*blockUnit, snakeUnit, snakeUnit, null);         
                        break;
                    case LEFT:
                        g.drawImage(snakeHeadLeft, s.getX()*blockUnit, s.getY()*blockUnit, snakeUnit, snakeUnit, null);         
                        break;
                    case RIGHT:
                        g.drawImage(snakeHeadRight, s.getX()*blockUnit, s.getY()*blockUnit, snakeUnit, snakeUnit, null);         
                        break;
                }
            }else if(snakeCount == 0){
                switch(s.getCellDirection()){
                    case DOWN:
                        g.drawImage(snakeTailDown, s.getX()*blockUnit, s.getY()*blockUnit, snakeUnit, snakeUnit, null);         
                        break;
                    case UP:
                        g.drawImage(snakeTailUp, s.getX()*blockUnit, s.getY()*blockUnit, snakeUnit, snakeUnit, null);         
                        break;
                    case LEFT:
                        g.drawImage(snakeTailLeft, s.getX()*blockUnit, s.getY()*blockUnit, snakeUnit, snakeUnit, null);         
                        break;
                    case RIGHT:
                        g.drawImage(snakeTailRight, s.getX()*blockUnit, s.getY()*blockUnit, snakeUnit, snakeUnit, null);         
                        break;
                }
            }else{
                switch(s.getCellDirection()){
                    case DOWN:
                    case UP:
                        g.drawImage(snakeSegmentVertical, s.getX()*blockUnit, s.getY()*blockUnit, snakeUnit, snakeUnit, null);         
                        break;
                    case LEFT:
                    case RIGHT:
                        g.drawImage(snakeSegmentHorizontal, s.getX()*blockUnit, s.getY()*blockUnit, snakeUnit, snakeUnit, null);         
                        break;
                }            }
            snakeCount++;
        }

        foodItem = board.getFood();
        if(foodItem != null){
            switch(foodItem.getFoodType()){
                case APPLE:
                    g.drawImage(apple, foodItem.getFoodCell().getX()*blockUnit, foodItem.getFoodCell().getY()*blockUnit, snakeUnit, snakeUnit, null);
                    break;
                case BANANA:
                    g.drawImage(banana, foodItem.getFoodCell().getX()*blockUnit, foodItem.getFoodCell().getY()*blockUnit, snakeUnit, snakeUnit, null);
                    break;
                case PEAR:
                    g.drawImage(pear, foodItem.getFoodCell().getX()*blockUnit, foodItem.getFoodCell().getY()*blockUnit, snakeUnit, snakeUnit, null);
                    break;
                case ORANGE:
                    g.drawImage(orange, foodItem.getFoodCell().getX()*blockUnit, foodItem.getFoodCell().getY()*blockUnit, snakeUnit, snakeUnit, null);
                    break;
            }
        }
    }


    public void gameOver() {
        moving = false;
        JLabel label = new JLabel("You lose");
        label.setBounds(screenWidth/2, screenHeight/2, screenWidth/2, 100);
        add(label);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (moving) {
            if(snake.isAlive()){
                board.processSnakeMovement(snake.getSnakeDirection());
            }else{
                gameOver();
            }
            repaint();
        }
    }
}
