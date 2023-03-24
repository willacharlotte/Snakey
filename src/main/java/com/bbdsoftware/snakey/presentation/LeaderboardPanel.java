package com.bbdsoftware.snakey.presentation;

import com.bbdsoftware.snakey.controllers.DatabaseController;
import com.bbdsoftware.snakey.domain.User;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class LeaderboardPanel extends JPanel {
    static final int screenWidth = 600;
    static final int screenHeight = 600;

    public LeaderboardPanel(User user) {
        this.setBackground(new Color(176, 176, 176));
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setFocusable(true);

        JLabel label = new JLabel("LEADERBOARD");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Verdana", Font.PLAIN, 36));
        label.setPreferredSize(new Dimension(400, 100));
        label.setForeground(Color.white);
        add(label);

        DatabaseController db = new DatabaseController();
        if (user != null) {
            DisplayScores(db.GetPlayerRecord(), user);
        } else {
            DisplayScores(db.GetPlayerRecord());
        }

    }


    /**
     * @param scores Takes in hashmap from db.getscores
     * @param user   Takes in user from User class
     */
    private void DisplayScores(HashMap<String, Integer> scores, User user) {
        JLabel recordLabel;
        List<String> nameList;
        DatabaseController db = new DatabaseController();

        nameList = scores.keySet().stream().limit(10).collect(Collectors.toList());
        for (int i = 0; i <= nameList.size() - 1; i++) {
            recordLabel = new JLabel((i + 1) + ". " + nameList.get(i) + " " + scores.get(nameList.get(i)));
            recordLabel.setHorizontalAlignment(JLabel.LEFT);
            recordLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
            recordLabel.setPreferredSize(new Dimension(300, 40));
            recordLabel.setForeground(new Color(255, 255, 255));

            if ((user.getUsername()).equalsIgnoreCase(nameList.get(i))) {
                recordLabel.setForeground(new Color(8, 168, 13));
            }

            add(recordLabel);
        }

        if (nameList.stream().noneMatch(e -> e.equalsIgnoreCase(user.getUsername()))) {
            int rowCount = db.GetUserRow(user);
            recordLabel = new JLabel((rowCount) + ". " + user.getUsername() + " " + user.getScore());
            recordLabel.setHorizontalAlignment(JLabel.LEFT);
            recordLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
            recordLabel.setPreferredSize(new Dimension(300, 40));
            recordLabel.setForeground(new Color(255, 0, 0));
            add(recordLabel);
        }
    }


    /**
     * Display scores without user
     *
     * @param scores Takes in hashmap from db.getscores
     */
    private void DisplayScores(HashMap<String, Integer> scores) {
        JLabel recordLabel;
        List<String> nameList;

        nameList = scores.keySet().stream().limit(10).collect(Collectors.toList());

        for (int i = 0; i <= nameList.size() - 1; i++) {

            recordLabel = new JLabel((i + 1) + ". " + nameList.get(i) + " " + scores.get(nameList.get(i)));
            recordLabel.setHorizontalAlignment(JLabel.LEFT);
            recordLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
            recordLabel.setPreferredSize(new Dimension(300, 40));
            recordLabel.setForeground(new Color(255, 255, 255));
            add(recordLabel);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.setColor(new Color(6, 129, 10));
        g.fillRect(0, 0, 600, 100);
    }

}
