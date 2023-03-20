package com.bbd.levelup2023.java.domain;

import com.bbd.levelup2023.java.enums.Colour;

public class Apple extends Food{
    private String foodName;
    private int points;
    private Colour foodColour;
    private int length;
    private Cell foodCell;

    public Apple(Cell food_cell){
        this.foodName = "Apple";
        this.points = 1;
        this.foodColour = Colour.RED;
        this.length = 2;

        this.foodCell = food_cell;
    }

    @Override
    public String getFoodName() {
        return foodName;
    }

    @Override
    public int getPoints() {
        return points;
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
                ", points=" + points +
                ", foodColour=" + foodColour +
                ", length=" + length +
                ", foodCell=" + foodCell +
                '}';
    }
}