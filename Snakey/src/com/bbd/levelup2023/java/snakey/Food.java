package com.bbd.levelup2023.java.snakey;

public abstract class Food{
    private String foodName;
    private int points;
    private Colour foodColour;
    private int length;
    private Cell foodCell;

    public Food(){ }

    public String getFoodName() {
        return foodName;
    }

    public int getPoints() {
        return points;
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
        return "Food{" +
                "foodName='" + foodName + '\'' +
                ", points=" + points +
                ", foodColour=" + foodColour +
                ", length=" + length +
                ", foodCell=" + foodCell +
                '}';
    }
}