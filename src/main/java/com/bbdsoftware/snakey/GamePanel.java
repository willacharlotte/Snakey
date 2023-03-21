package com.bbdsoftware.snakey;

import com.bbdsoftware.snakey.domain.Cell;
import com.bbdsoftware.snakey.domain.Food;
import com.bbdsoftware.snakey.domain.Orange;
import com.bbdsoftware.snakey.enums.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    static final int unit = 25;
    static final int gameUnit = (600 * 600) / unit;
    Food orange;

    final int movex[] = new int[gameUnit];
    final int movey[] = new int[gameUnit];
    int screenWidth,screenHeight=600;
    Direction direction = Direction.RIGHT;
    int speed = 100;
    boolean moving = false;
    Timer timer = new Timer(speed, this);

    Snake player = new Snake();


    ArrayList<Rectangle> snakeBlocks = new ArrayList<>();

    GamePanel() {
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(600, 600));
        this.setFocusable(true);
        this.addKeyListener(new Keyboard());
        timer.start();
        startGame();
    }

    public void startGame() {
snakeBlocks.add(new Rectangle(10,10));
        generateFood();
        moving = true;

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(orange.getFoodCell().getX(), orange.getFoodCell().getY(), unit, unit);
        System.out.println(snakeBlocks.size());


        for (int i = 0; i < snakeBlocks.size(); i++) {
            if (i == 0) {
                g.setColor(Color.green);
                g.fillRect(movex[i], movey[i],unit,unit);
            } else {
                g.setColor(new Color(45, 180, 0));
                g.fillRect(movex[i], movey[i], unit, unit);
            }
            checkFood(movex[i], movey[i]);
            //checkWall(movex[i],movey[i]);
        }

        if (!player.isAlive()){
            g.fillRect(300, 300, unit, unit);
            g.setColor(Color.gray);
            moving=false;

            JLabel l = new JLabel("You lose");
            l.setBounds(100,100,100,100);

            add(l);
  }
//        for (int i = 0; i <= snakeBlocks.size(); i++) {
//            if (i == 0) {
//                g.setColor(Color.green);
//                g.fillRect(movex[i], movey[i], unit, unit);
//            } else {
//                g.setColor(new Color(45, 180, 0));
//                g.fillRect(movex[i], movey[i], unit, unit);
//
//            }
//
//            checkFood(movex[i],movey[i]);
//        }

    }

    public void generateFood() {
        Random random = new Random();
        int x = random.nextInt(575) + 1;

        while (x % 25 != 0) {
            x = random.nextInt(575) + 1;
        }
        int y = random.nextInt(575) + 1;
        while (y % 25 != 0) {
            y = random.nextInt(575) + 1;
        }
        orange = new Orange(new Cell(x, y));
    }



    public void checkFood(int x, int y) {
        if (x == orange.getFoodCell().getX() && y == orange.getFoodCell().getY()) {
           snakeBlocks.add(new Rectangle(25,25));
            player.eatFood(orange);
            generateFood();
        }
    }

    public void gameOver(){
        JLabel label = new JLabel("You Lose!");

    }
    public void move() {
        for (int i = snakeBlocks.size(); i > 0; i--) {
            movex[i] = movex[i - 1];
            movey[i] = movey[i - 1];
        }

        switch (direction) {
            case UP:
                movey[0] = movey[0] - unit;
                break;
            case DOWN:
                movey[0] = movey[0] + unit;
                break;
            case LEFT:
                movex[0] = movex[0] - unit;
                break;
            case RIGHT:
                movex[0] = movex[0] + unit;
                break;
        }
    }

    //        public void moveSnake(Direction direction){
//        int new_y = player.x[0];
//        int new_x =player.y[0];
//
//        if (direction.equals(Direction.UP)){
//            new_y -= 1;
//        }
//        else if (direction.equals(Direction.DOWN)){
//            new_y += 1;
//        }
//        else if (direction.equals(Direction.LEFT)){
//            new_x -= 1;
//        }
//        else{
//            new_x += 1;
//        }
//
//    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (moving){
            move();
            repaint();
        }


    }


    public class Keyboard extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);

            //Movements here
            switch (e.getKeyCode()) {
                case KeyEvent.VK_DOWN:
                    direction = Direction.DOWN;
                    break;
                case KeyEvent.VK_UP:
                    direction = Direction.UP;
                    break;
                case KeyEvent.VK_LEFT:
                    direction = Direction.LEFT;
                    break;
                case KeyEvent.VK_RIGHT:
                    direction = Direction.RIGHT;
                    break;
            }
        }
    }


}
