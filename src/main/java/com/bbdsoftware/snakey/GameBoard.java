package com.bbdsoftware.snakey;
import javax.swing.*;

public class GameBoard extends JFrame {
    public GameBoard() {
        this.add(new GameController());
        this.setTitle("Snake");
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

    // public void closeThisFrame(){
    //     this.dispose();
    // }
}