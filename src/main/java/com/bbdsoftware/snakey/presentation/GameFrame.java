package com.bbdsoftware.snakey.presentation;
import javax.swing.*;

import com.bbdsoftware.snakey.controllers.GameController;

public class GameFrame extends JFrame {
    public GameFrame() {
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