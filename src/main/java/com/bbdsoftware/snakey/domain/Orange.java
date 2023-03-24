package com.bbdsoftware.snakey.domain;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.bbdsoftware.snakey.enums.Audio;
import com.bbdsoftware.snakey.enums.FoodTypes;

import static com.bbdsoftware.snakey.controllers.AudioController.AUDIO_CONTROLLER;

public class Orange  extends Food{
    private Random random = new Random();
    private int xPos1 = 0;
    private int xPos2 = 0;
    private int yPos1 = 0;
    private int yPos2 = 0;
    private int tempColor1 = Color.TRANSLUCENT;
    private int tempColor2 = Color.TRANSLUCENT;
    private boolean initial = false;
    private int swapCounter = 0;
    private int[][] originalColors;

    public Orange(Cell foodCell){
        super(FoodTypes.ORANGE, 50, Color.orange, 5, foodCell);
    }

    @Override
    public void doSpecial(Snake snake) {
        AUDIO_CONTROLLER.play(Audio.CRUNCH_ORANGE);
    }

    @Override
    public BufferedImage applyImageEffect(BufferedImage image){
        if(initial){
            if(swapCounter < 20){        
                xPos1 = random.nextInt(image.getWidth());
                xPos2 = random.nextInt(image.getWidth());
                yPos1 = random.nextInt(image.getHeight());
                yPos2 = random.nextInt(image.getHeight());

                tempColor1 = image.getRGB(xPos1, yPos1);
                tempColor2 = image.getRGB(xPos2, yPos2);
                image.setRGB(xPos1, yPos1, tempColor2);
                image.setRGB(xPos2, yPos2, tempColor1);
                swapCounter++;
            }else{
                for(int i = 0; i < image.getWidth(); i++){
                    for(int j = 0; j < image.getHeight(); j++){
                        image.setRGB(i, j, originalColors[i][j]);
                    }
                }      
                swapCounter = 0;          
            }
        }else{
            initial = true;
            originalColors = new int[image.getWidth()][image.getHeight()];
            for(int i = 0; i < image.getWidth(); i++){
                for(int j = 0; j < image.getHeight(); j++){
                    originalColors[i][j] = image.getRGB(i, j);
                }
            }
        }
        return image;    
    }
}