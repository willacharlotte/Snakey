package com.bbdsoftware.snakey.presentation;
import javax.swing.*;

public class MenuFrame extends JFrame {

    public MenuFrame() {
        this.add(new MenuPanel());
        this.setTitle("Snake");
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocation(0,0);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}