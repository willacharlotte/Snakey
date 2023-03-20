package com.bbd.levelup2023.java.snakey;

public class Apple extends Food{
    private String foodName;
    private int points;
    private Colour foodColour;
    private int length;
    private Cell foodCell;

    public Apple(){
        this.foodName = "Apple";
        this.points = 1;
        this.foodColour = Colour.RED;
        this.length = 2;
    }

    public Apple(Cell fruit_cell){
        this();
        this.foodCell = fruit_cell;

    }
}