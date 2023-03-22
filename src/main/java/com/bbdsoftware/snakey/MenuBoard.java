package com.bbdsoftware.snakey;

import javax.swing.*;

public class MenuBoard extends JFrame {

    MenuBoard() {
        this.add(new Menu());
        this.setTitle("Snake");
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

}

