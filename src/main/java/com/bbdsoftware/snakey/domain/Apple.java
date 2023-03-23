package com.bbdsoftware.snakey.domain;
import java.awt.Color;

import com.bbdsoftware.snakey.enums.FoodTypes;

public class Apple extends Food{
    public Apple(Cell foodCell){
        super(FoodTypes.APPLE, 10, Color.red, 2, foodCell);
    }

    @Override
    public void doSpecial(Snake snake) {

    }
}