package com.bbdsoftware.snakey.presentation;

import javax.swing.*;

import com.bbdsoftware.snakey.controllers.GameController;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends JFrame {
    public GameFrame() {
        this.add(new GameController());
        this.setTitle("Snake");
        this.setResizable(false);
        this.pack();
        this.setVisible(true);

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                new MenuFrame();
            }
        });
    }

}