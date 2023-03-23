package com.bbdsoftware.snakey.presentation;
import com.bbdsoftware.snakey.domain.User;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LeaderboardFrame extends JFrame{

    public LeaderboardFrame(User user) {
        this.add(new LeaderboardPanel(user));
        this.setTitle("Leaderboard");
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocation(0,0);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                if (user==null){
                    new MenuFrame();
                }

            }
        });

    }
}