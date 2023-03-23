package com.bbdsoftware.snakey.domain;
import java.awt.Color;

import com.bbdsoftware.snakey.enums.FoodTypes;

public class Orange  extends Food{
    public Orange(Cell foodCell){
        super(FoodTypes.ORANGE, 50, Color.orange, 5, foodCell);
    }

    @Override
    public void doSpecial(Snake snake) {

    }
}