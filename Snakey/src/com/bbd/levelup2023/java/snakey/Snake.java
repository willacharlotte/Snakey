package com.bbd.levelup2023.java.snakey;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Snake {
    private Cell head;
    //private Cell[] snakeBlocks;
    private List<Cell> snakeBlocks = new ArrayList<>();
    private int length;
    private List<Food> foodItems;
    private boolean isAlive = true;
    private Direction snakeDirection;
    private float speed = 1;

    public Snake(){
        this.length = 1;
    }

    public void setHead(Cell head){
        if (this.isAlive){
            this.head = head;
            //snakeBlocks.add(head);
        }
    }

    public Cell getHead(){
        return this.head;
    }

    public void moveSnake(Direction direction){
        if (this.isAlive){
            System.out.println("snake moves " + direction);
            this.snakeDirection = direction;
        }
    }

    public boolean getIsAlive(){
        return this.isAlive;
    }

    public void killSnake(){
        System.out.println("snake dead");
        this.isAlive = false;
    }

    public List<Cell> getSnakeBlocks() {
        return snakeBlocks;
    }



    @Override
    public String toString() {
        return "Snake{" +
                "head=" + head +
                ", snakeBlocks=" + snakeBlocks +
                ", length=" + length +
                ", foodItems=" + foodItems +
                ", isAlive=" + isAlive +
                ", snakeDirection=" + snakeDirection +
                ", speed=" + speed +
                '}';
    }

}
