package com.bbdsoftware.snakey.domain;
import java.awt.Color;

import com.bbdsoftware.snakey.enums.FoodTypes;

public class Pear extends Food{
    public Pear(Cell foodCell){
        super(FoodTypes.PEAR, 0.6F, Color.green, 5, foodCell);
    }

    @Override
    public void doSpecial(Snake snake) {

    }
}