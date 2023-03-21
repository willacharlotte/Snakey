package com.bbdsoftware.snakey.domain;

import com.bbdsoftware.snakey.enums.Colour;

public class Apple extends Food{
    public Apple(Cell foodCell){
        super("Apple", 0.5F, Colour.RED, 2, foodCell);
    }

    @Override
    public void doSpecial(Snake snake) {

    }
}