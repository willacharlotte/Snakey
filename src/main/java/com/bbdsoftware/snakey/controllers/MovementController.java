package com.bbdsoftware.snakey.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.bbdsoftware.snakey.domain.Snake;;

public class MovementController implements KeyListener {
    private final Snake snake;

    public MovementController(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        switch (e.getKeyCode()) {
            case 87: case 38:
                System.out.println("I moved up!");
                break;
            case 65: case 37:
                System.out.println("I moved left!");
                break;
            case 83: case 40:
                System.out.println("I moved down!");
                break;
            case 68: case 39:
                System.out.println("I moved right!");
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
