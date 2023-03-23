package com.bbdsoftware.snakey.presentation;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuPanel extends JPanel {
    JButton startButton = new JButton("Start Game");
    JButton quitButton = new JButton("Quit");
    JButton leaderboardButton = new JButton("Leaderboard");
    JLabel heading = new JLabel("SNAKEY");
    Font pixelFontLarge, pixelFontSmall, pixelFontExtraLarge;
    BufferedImage snakeImage;
    
    MenuPanel() {
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(600, 600));
        this.setFocusable(true);
        loadResources();
        startMenu();
    }

    void startMenu(){
        repaint();
        startButton.addActionListener(e -> {
            new GameFrame();
            closeParentFrame();
        });
        leaderboardButton.addActionListener(e -> {
            System.out.println("clicked");
        });
        quitButton.addActionListener(e -> {
            int input = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to quit?", "Select option", JOptionPane.YES_NO_OPTION);
            if (input == 0) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        g.drawImage(snakeImage, 250, 20, 100, 100, null);

        heading.setBounds(0, 140, 600, 100);
        heading.setFont(pixelFontExtraLarge);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setVerticalAlignment(SwingConstants.CENTER);
        heading.setOpaque(true);
        heading.setBackground(Color.BLACK);
        heading.setForeground(Color.GREEN);   
        add(heading);

        startButton.setBounds(100, 280, 400, 80);
        startButton.setFont(pixelFontLarge);
        startButton.setHorizontalAlignment(SwingConstants.CENTER);
        startButton.setVerticalAlignment(SwingConstants.CENTER);
        startButton.setOpaque(true);
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.GREEN); 
        add(startButton);

        leaderboardButton.setBounds(100, 380, 400, 80);
        leaderboardButton.setFont(pixelFontLarge);
        leaderboardButton.setHorizontalAlignment(SwingConstants.CENTER);
        leaderboardButton.setVerticalAlignment(SwingConstants.CENTER);
        leaderboardButton.setOpaque(true);
        leaderboardButton.setBackground(Color.BLACK);
        leaderboardButton.setForeground(Color.GREEN); 
        add(leaderboardButton);

        quitButton.setBounds(100, 480, 400, 80);
        quitButton.setFont(pixelFontLarge);
        quitButton.setHorizontalAlignment(SwingConstants.CENTER);
        quitButton.setVerticalAlignment(SwingConstants.CENTER);
        quitButton.setOpaque(true);
        quitButton.setBackground(Color.BLACK);
        quitButton.setForeground(Color.GREEN); 
        add(quitButton);
    }

    private void closeParentFrame(){
        final Window parentWindow = SwingUtilities.getWindowAncestor(this);
        parentWindow.dispose();
    }

    
    private void loadResources(){
        try {
            snakeImage = ImageIO.read(new File("resources/snek.png"));
        } catch (IOException e) {
            // Add error handing here
        }
        try {
            pixelFontLarge = Font.createFont(Font.TRUETYPE_FONT, new File("resources/terminal-grotesque.ttf"));
            pixelFontLarge = pixelFontLarge.deriveFont(60f); 
            pixelFontSmall = pixelFontLarge.deriveFont(20f);
            pixelFontExtraLarge = pixelFontLarge.deriveFont(120f);
        } catch (FontFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
