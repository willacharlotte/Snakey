package com.bbdsoftware.snakey.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;;

public class MovementController implements KeyListener {
  public static void main(String[] args) {
   
  }

  public MovementController() { // add snake instance here?

  }

  @Override
  public void keyTyped(KeyEvent e) {
    // not needed
  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyChar()) {
      case 'w':
        System.out.println("I moved up!");
        // snake.moveUp() ??
        break;
      case 'a':
        System.out.println("I moved left!");
        // snake.moveLeft() ??
        break;
      case 's':
        System.out.println("I moved down!");
        // snake.moveDown() ??
        break;
      case 'd':
        System.out.println("I moved right!");
        // snake.moveRight() ??
        break;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    // not needed (yet?)
  }

}
