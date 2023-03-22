package com.bbdsoftware.snakey.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.bbdsoftware.snakey.domain.Snake;
import com.bbdsoftware.snakey.enums.Direction;;

public class MovementController implements KeyListener {
    private final Snake snake;

    public MovementController(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
           case 87: case 38:
                snake.setSnakeDirection(Direction.UP);
                break;
            case 65: case 37:
                snake.setSnakeDirection(Direction.LEFT);
                break;
            case 83: case 40:
                snake.setSnakeDirection(Direction.DOWN);
                break;
            case 68: case 39:
                snake.setSnakeDirection(Direction.RIGHT);
                break; 
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
