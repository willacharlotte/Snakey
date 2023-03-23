package com.bbdsoftware.snakey.domain;

import java.awt.Color;
import com.bbdsoftware.snakey.enums.FoodTypes;

public abstract class Food{
    private FoodTypes foodType;
    private int score;
    private Color foodColour;
    private int length;
    private Cell foodCell;

    public Food(){ }

    protected Food(FoodTypes foodType, int score, Color foodColour, int length, Cell foodCell){
        this.foodType = foodType;
        this.score = score;
        this.foodColour = foodColour;
        this.length = length;
        this.foodCell = foodCell;
    }

    public abstract void doSpecial(Snake snake);

    public FoodTypes getFoodType() {
        return foodType;
    }

    public int getScore() {
        return score;
    }

    public Color getFoodColour() {
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
                "foodType='" + foodType + '\'' +
                ", score=" + score +
                ", foodColour=" + foodColour +
                ", length=" + length +
                ", foodCell=" + foodCell +
                '}';
    }
}