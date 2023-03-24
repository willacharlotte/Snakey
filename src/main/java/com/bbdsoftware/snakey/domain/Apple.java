package com.bbdsoftware.snakey.domain;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.bbdsoftware.snakey.enums.Audio;
import com.bbdsoftware.snakey.enums.FoodTypes;

import static com.bbdsoftware.snakey.controllers.AudioController.AUDIO_CONTROLLER;

public class Apple extends Food{
    private Random random = new Random();
    private int previousColor = Color.GREEN.getRGB();
    private int x = 0;
    private int y = 0;

    public Apple(Cell foodCell){
        super(FoodTypes.APPLE, 10, Color.red, 2, foodCell);
    }

    @Override
    public void doSpecial(Snake snake) {
        AUDIO_CONTROLLER.play(Audio.CRUNCH_BANANA);
    }

    @Override
    public BufferedImage applyImageEffect(BufferedImage image){
        if(image.getRGB(x, y) == Color.GREEN.getRGB()){
            image.setRGB(x, y, previousColor);
            x = random.nextInt((image.getWidth()));
            y = random.nextInt((image.getHeight()));
        }else{
            previousColor = image.getRGB(x, y);
            image.setRGB(x, y, Color.GREEN.getRGB());     
        }
        
        return image;
    }
}