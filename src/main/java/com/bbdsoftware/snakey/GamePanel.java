package com.bbdsoftware.snakey;

import com.bbdsoftware.snakey.controllers.MovementController;
import com.bbdsoftware.snakey.domain.Cell;
import com.bbdsoftware.snakey.domain.Food;
import com.bbdsoftware.snakey.domain.Orange;
import com.bbdsoftware.snakey.domain.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    static final int unit = 25;
    Food orange;
    static final int screenWidth=600;
    static final int screenHeight = 600;

    final int gameUnit = (screenHeight * screenWidth) / unit;
    final int coordinateX[] = new int[gameUnit];
    final int coordinateY[] = new int[gameUnit];

    int speed = 100;
    boolean moving = false;
    Timer timer = new Timer(speed, this);

    Snake player = new Snake();

    ArrayList<Rectangle> snakeBlocks = new ArrayList<>();

    GamePanel() {
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setFocusable(true);
        this.addKeyListener(new MovementController(player));
        timer.start();
        startGame();
    }

    private void startGame() {
        snakeBlocks.add(new Rectangle(10, 10));
        moving = true;
        generateFood();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(orange.getFoodCell().getX(), orange.getFoodCell().getY(), unit, unit);

        for (int i = 0; i < snakeBlocks.size(); i++) {
            if (i == 0) {
                g.setColor(Color.green);
                g.fillRect(coordinateX[i], coordinateY[i], unit, unit);
            } else {
                g.setColor(new Color(45, 180, 0));
                g.fillRect(coordinateX[i], coordinateY[i], unit, unit);
            }
            checkFood(coordinateX[i], coordinateY[i]);
        }

    }

    public void generateFood() {
        Random random = new Random();
        int x = Math.round((random.nextInt(screenWidth-unit))/25)*25;
        int y = Math.round((random.nextInt(screenHeight-unit))/25)*25;
        orange = new Orange(new Cell(x, y));
    }


    public void checkFood(int x, int y) {
        if (x == orange.getFoodCell().getX() && y == orange.getFoodCell().getY()) {
            snakeBlocks.add(new Rectangle(unit, unit));
            player.eatFood(orange);
            generateFood();
        }
    }

    public void gameOver() {
        if (!player.isAlive()) {
            moving = false;
            JLabel label = new JLabel("You lose");
            label.setBounds(100, 100, 100, 100);
            add(label);
        }
    }

    public void move() {
        for (int i = snakeBlocks.size(); i > 0; i--) {
            coordinateX[i] = coordinateX[i - 1];
            coordinateY[i] = coordinateY[i - 1];
        }

        switch (player.getSnakeDirection()) {
            case UP:
                coordinateY[0] = coordinateY[0] - unit;
                break;
            case DOWN:
                coordinateY[0] = coordinateY[0] + unit;
                break;
            case LEFT:
                coordinateX[0] = coordinateX[0] - unit;
                break;
            case RIGHT:
                coordinateX[0] = coordinateX[0] + unit;
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (moving) {
            move();
            repaint();
        }

    }

}
