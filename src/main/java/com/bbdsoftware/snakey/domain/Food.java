package com.bbdsoftware.snakey.domain;

import com.bbdsoftware.snakey.enums.Colour;

public abstract class Food{
    private String foodName;
    private float score;
    private Colour foodColour;
    private int length;
    private Cell foodCell;

    public Food(){ }

    protected Food(String foodName, float score, Colour foodColour, int length, Cell foodCell){
        this.foodName = foodName;
        this.score = score;
        this.foodColour = foodColour;
        this.length = length;
        this.foodCell = foodCell;
    }

    public abstract void doSpecial(Snake snake);

    public String getFoodName() {
        return foodName;
    }

    public float getScore() {
        return score;
    }

    public Colour getFoodColour() {
        return foodColour;
    }

    public int getLength() {
        return length;
    }

    public Cell getFoodCell() {
        return foodCell;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "foodName='" + foodName + '\'' +
                ", score=" + score +
                ", foodColour=" + foodColour +
                ", length=" + length +
                ", foodCell=" + foodCell +
                '}';
    }
}