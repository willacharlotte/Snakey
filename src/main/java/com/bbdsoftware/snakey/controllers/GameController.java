package com.bbdsoftware.snakey.controllers;

import com.bbdsoftware.snakey.domain.Cell;
import com.bbdsoftware.snakey.domain.Food;
import com.bbdsoftware.snakey.domain.Snake;
import com.bbdsoftware.snakey.presentation.GameFrame;
import com.bbdsoftware.snakey.presentation.MenuFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
    BoardController board = new BoardController(numBlocks);
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
    Font pixelFontLarge, pixelFontSmall;

    public GameController() {
        snake = board.getMySnake();
        this.setBackground(Color.gray);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setFocusable(true);
        this.addKeyListener(new MovementController(snake));

        foodItem = board.getFood();
        foodX = foodItem.getFoodCell().getX();
        foodY = foodItem.getFoodCell().getY();

        loadResources();
        timer.start();
        startGame();
    }

    private void startGame() {
        moving = true;
    }

    private void loadResources(){
        try {
            snakeSegmentHorizontal = ImageIO.read(new File("resources/snakeSegmentHorizontal.png"));
            snakeSegmentVertical = ImageIO.read(new File("resources/snakeSegmentVertical.png"));
            
            snakeHeadUp = ImageIO.read(new File("resources/snakeHeadUp.png"));
            snakeHeadDown = ImageIO.read(new File("resources/snakeHeadDown.png"));
            snakeHeadLeft = ImageIO.read(new File("resources/snakeHeadLeft.png"));
            snakeHeadRight = ImageIO.read(new File("resources/snakeHeadRight.png"));
            
            snakeTailUp = ImageIO.read(new File("resources/snakeTailUp.png"));
            snakeTailDown = ImageIO.read(new File("resources/snakeTailDown.png"));
            snakeTailLeft = ImageIO.read(new File("resources/snakeTailLeft.png"));
            snakeTailRight = ImageIO.read(new File("resources/snakeTailRight.png"));

            apple = ImageIO.read(new File("resources/apple.png"));
            banana = ImageIO.read(new File("resources/banana.png"));
            orange = ImageIO.read(new File("resources/orange.png"));
            pear = ImageIO.read(new File("resources/pear.png"));
        } catch (IOException e) {
            // Add error handing here
        }

        try {
            pixelFontLarge = Font.createFont(Font.TRUETYPE_FONT, new File("resources/terminal-grotesque.ttf"));
            pixelFontLarge = pixelFontLarge.deriveFont(60f); 
            pixelFontSmall = pixelFontLarge.deriveFont(20f);
        } catch (FontFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
        // TO DO: ADD HIGH SCORE HERE

        JButton quitButton = new JButton("QUIT");
        JLabel gameOverLabel = new JLabel("Game Over");
        JLabel nameLabel = new JLabel("ENTER YOUR NAME:");
        JTextField nameField = new JTextField(10);
        JButton submitNameButton = new JButton("SUBMIT");
        JButton restartButton = new JButton("RESTART");
        addEnterUsernameFields(gameOverLabel, nameLabel, nameField, submitNameButton);
    
        submitNameButton.setEnabled(false);

        nameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent event){
                String content = nameField.getText();
                if(!content.equals("")){
                    submitNameButton.setEnabled(true);
                }else{
                    submitNameButton.setEnabled(false);
                }
            }
        });

        submitNameButton.addActionListener(e -> {
            removeJFrameComponent(nameLabel);
            removeJFrameComponent(nameField);
            removeJFrameComponent(submitNameButton);
            addEndOfGameFields(restartButton, quitButton);
            repaint();


            // Add username + score to DB here
            // USERNAME: nameField.getText();
            // SCORE: snake.getScore();
            
        });

        restartButton.addActionListener(e -> {
            new GameFrame();
            closeParentFrame();
        });

 
        
        quitButton.addActionListener(e -> {
            new MenuFrame();
            closeParentFrame();
        });
    }

    private void addEnterUsernameFields(JLabel gameOverLabel, JLabel nameLabel, JTextField nameField, JButton submitNameButton){
        gameOverLabel.setBounds(0, screenHeight/2-300, screenWidth, 100);
        gameOverLabel.setFont(pixelFontLarge);
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameOverLabel.setVerticalAlignment(SwingConstants.CENTER);
        gameOverLabel.setOpaque(true);
        gameOverLabel.setBackground(Color.BLACK);
        gameOverLabel.setForeground(Color.GREEN);   
        add(gameOverLabel);

        nameLabel.setBounds(115, screenHeight/2-175, 200, 50);
        nameLabel.setFont(pixelFontSmall);
        nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        nameLabel.setVerticalAlignment(SwingConstants.CENTER);
        nameLabel.setOpaque(true);
        nameLabel.setBackground(Color.GRAY);
        nameLabel.setForeground(Color.BLACK);  
        add(nameLabel);

        nameField.setBounds(335, screenHeight/2-175, 300, 50);
        nameField.setFont(pixelFontSmall);
        nameField.setHorizontalAlignment(SwingConstants.LEFT);
        nameField.setOpaque(true);
        nameField.setBackground(Color.WHITE);
        nameField.setForeground(Color.BLACK); 
        nameField.setMargin(new Insets(0, 20, 0, 0));
        add(nameField);

        submitNameButton.setBounds(screenWidth/2-150, screenHeight/2-100, 300, 80);
        submitNameButton.setFont(pixelFontLarge);
        submitNameButton.setHorizontalAlignment(SwingConstants.CENTER);
        submitNameButton.setVerticalAlignment(SwingConstants.CENTER);
        submitNameButton.setOpaque(true);
        submitNameButton.setBackground(Color.BLACK);
        submitNameButton.setForeground(Color.GREEN); 
        add(submitNameButton);
    }


    private void addEndOfGameFields(JButton restartButton, JButton quitButton){
        restartButton.setBounds(screenWidth/2-200, screenHeight/2-175, 400, 100);
        restartButton.setFont(pixelFontLarge);
        restartButton.setHorizontalAlignment(SwingConstants.CENTER);
        restartButton.setVerticalAlignment(SwingConstants.CENTER);
        restartButton.setOpaque(true);
        restartButton.setBackground(Color.BLACK);
        restartButton.setForeground(Color.GREEN);  
        add(restartButton);

        quitButton.setBounds(screenWidth/2-200, screenHeight/2-50, 400, 100);
        quitButton.setFont(pixelFontLarge);
        quitButton.setHorizontalAlignment(SwingConstants.CENTER);
        quitButton.setVerticalAlignment(SwingConstants.CENTER);
        quitButton.setOpaque(true);
        quitButton.setBackground(Color.BLACK);
        quitButton.setForeground(Color.GREEN); 
        add(quitButton);
    }

    
    private void removeJFrameComponent(JButton... buttons){
        for (JButton jButton : buttons) {
            remove(jButton);            
        }
    }

    private void removeJFrameComponent(JLabel... labels){
        for (JLabel jLabel : labels) {
            remove(jLabel);            
        }
    }

    private void removeJFrameComponent(JTextField... textFields){
        for (JTextField jTextField : textFields) {
            remove(jTextField);
        }
    }
    private void closeParentFrame(){
        final Window parentWindow = SwingUtilities.getWindowAncestor(this);
        parentWindow.dispose();
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
