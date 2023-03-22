package com.bbdsoftware.snakey;

import javax.swing.*;

public class GameBoard extends JFrame {

    public GameBoard() {
        this.add(new GamePanel());
        this.setTitle("Snake");
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }


}
