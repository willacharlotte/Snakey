package com.bbd.levelup2023.java.domain;

import com.bbd.levelup2023.java.enums.Colour;

public class Orange  extends Food{
    private String foodName;
    private float score;
    private Colour foodColour;
    private int length;
    private Cell foodCell;

    public Orange(Cell food_cell){
        this.foodName = "Orange";
        this.score = 0.9F;
        this.foodColour = Colour.ORANGE;
        this.length = 5;

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
        return "Orange{" +
                "foodName='" + foodName + '\'' +
                ", score=" + score +
                ", foodColour=" + foodColour +
                ", length=" + length +
                ", foodCell=" + foodCell +
                '}';
    }
}