package com.bbdsoftware.snakey.domain;

import com.bbdsoftware.snakey.enums.Colour;

public class Orange  extends Food{
    public Orange(Cell foodCell){
        super("Orange", 0.9F, Colour.ORANGE, 5, foodCell);
    }

    @Override
    public void doSpecial(Snake snake) {

    }
}