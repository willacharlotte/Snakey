package com.bbdsoftware.snakey;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {
    JButton startButton = new JButton("Start Game");
    JButton quitButton = new JButton("Quit");
    JButton leaderboardButton = new JButton("Leaderboard");

    Menu() {
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(600, 600));
        this.setFocusable(true);

        startButton.setBounds(250, 50, 200, 40);
        leaderboardButton.setBounds(250, 100, 200, 40);
        quitButton.setBounds(250, 150, 200, 40);

        startMenu();

    }

    void startMenu(){
        add(startButton);
        add(leaderboardButton);
        add(quitButton);

        startButton.addActionListener(e -> {
            //get game board
            new GameBoard();
        });
        leaderboardButton.addActionListener(e -> {
            System.out.println("clicked");
            //get game board
        });
        quitButton.addActionListener(e -> {
            int input = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to quit?", "Select option", JOptionPane.YES_NO_OPTION);
            if (input == 0) {
                System.exit(0);
            }
        });

    }

}
