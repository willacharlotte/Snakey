package com.bbdsoftware.snakey;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends Board {
    JButton startButton = new JButton("Start Game");
    JButton quitButton = new JButton("Quit");
    JButton leaderboardButton = new JButton("Leaderboard");

    Menu(){
        startButton.setBounds(250,50,200,40);
        leaderboardButton.setBounds(250,100,200,40);
        quitButton.setBounds(250,150,200,40);
    }

    void startMenu(){
        board.add(startButton);
        board.add(leaderboardButton);
        board.add(quitButton);

      startButton.addActionListener(e -> {
          System.out.println("clicked");
          //get game board
      });
      leaderboardButton.addActionListener(e -> {
          System.out.println("clicked");
          //get game board
      });
      quitButton.addActionListener(e -> {
          int input = JOptionPane.showConfirmDialog(null,
                  "Are you sure you want to quit?","Select option",JOptionPane.YES_NO_OPTION);
          if (input==0){
              System.exit(0);
          }
      });
    }

}
