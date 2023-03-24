package com.bbdsoftware.snakey.domain;
import java.awt.Color;
import java.awt.image.BufferedImage;

import com.bbdsoftware.snakey.enums.Audio;
import com.bbdsoftware.snakey.enums.FoodTypes;

import static com.bbdsoftware.snakey.controllers.AudioController.AUDIO_CONTROLLER;

public class Banana  extends Food{
    private boolean initial = false;
    private boolean switcher = false;
    private int[][] originalColors;

    public Banana(Cell foodCell){
        super(FoodTypes.BANANA, 15, Color.yellow, 5, foodCell);
    }

    @Override
    public void doSpecial(Snake snake) {
        AUDIO_CONTROLLER.play(Audio.CRUNCH_BANANA);
    }

    @Override
    public BufferedImage applyImageEffect(BufferedImage image){
        if(initial){
            if(switcher){
                for(int i = 0; i < image.getWidth(); i++){
                    for(int j = 0; j < image.getHeight(); j++){
                        if(j%2==0){
                            image.setRGB(i, j, Color.TRANSLUCENT);
                        }
                    }
                }
                switcher = false;    
            }else{
                for(int i = 0; i < image.getWidth(); i++){
                    for(int j = 0; j < image.getHeight(); j++){
                        image.setRGB(i, j, originalColors[i][j]);
                    }
                }                
                switcher = true;
            }
        }else{
            initial = true;
            switcher = false;
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