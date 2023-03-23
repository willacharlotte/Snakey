package com.bbdsoftware.snakey.domain;
import java.awt.Color;

import com.bbdsoftware.snakey.enums.FoodTypes;

public class Banana  extends Food{
    public Banana(Cell foodCell){
        super(FoodTypes.BANANA, 15, Color.yellow, 5, foodCell);
    }

    @Override
    public void doSpecial(Snake snake) {

    }
}