package com.bbdsoftware.snakey.domain;

import com.bbdsoftware.snakey.enums.Colour;

public class Apple extends Food{
    private final String foodName;
    private final float score;
    private final Colour foodColour;
    private final int length;
    private final Cell foodCell;

    public Apple(Cell food_cell){
        this.foodName = "Apple";
        this.score = 0.5F;
        this.foodColour = Colour.RED;
        this.length = 2;

        this.foodCell = food_cell;
    }

    @Override
    public String getFoodName() {
        return foodName;
    }

    @Override
    public float getScore() {
        return score;
    }

    @Override
    public Colour getFoodColour() {
        return foodColour;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public Cell getFoodCell() {
        return foodCell;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "foodName='" + foodName + '\'' +
                ", score=" + score +
                ", foodColour=" + foodColour +
                ", length=" + length +
                ", foodCell=" + foodCell +
                '}';
    }
}