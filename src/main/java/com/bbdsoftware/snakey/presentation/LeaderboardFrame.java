package com.bbdsoftware.snakey.presentation;

import com.bbdsoftware.snakey.domain.User;

import javax.swing.*;

public class LeaderboardFrame extends JFrame{

    public LeaderboardFrame(User user) {
        this.add(new LeaderboardPanel(user));
        this.setTitle("Leaderboard");
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }


}
