package com.bbd.levelup2023.java.snakey;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Snake {
    private Cell head;
    //private Cell[] snakeBlocks;
    private final List<Cell> snakeBlocks = new ArrayList<>();
    private int length;
    private int currentLength = 1;
    private List<Food> foodItems;
    private boolean isAlive = true;
    private Direction snakeDirection;
    private float point = 1;

    public Snake(){
        this.length = 10;
    }

    /**
     * sets the head of the snake if the snake is alive
     * @param head the head cell
     */
    public void setHead(Cell head){
        if (this.isAlive){
            this.head = head;
            snakeBlocks.add(head);
        }
    }

    public Cell getHead(){
        return this.head;
    }

    /**
     * moves the snake and sets its direction
     * @param new_cell the new cell for snake to move to
     * @param direction the direction of the snake now
     */
    public void moveSnake(Cell new_cell, Direction direction){
        if (this.isAlive){
            //System.out.println("snake moves " + direction + " to " + new_cell);
            if (this.currentLength < this.length) {
                this.snakeBlocks.add(new_cell);
                this.head = new_cell;
                currentLength += 1;
            }
            else{
                this.snakeBlocks.remove(0);
                this.snakeBlocks.add(new_cell);
                this.head = new_cell;
            }
            this.snakeDirection = direction;
        }
    }

    public boolean getIsAlive(){
        return this.isAlive;
    }

    public void killSnake(){
        this.isAlive = false;
    }

    public List<Cell> getSnakeBlocks() {
        return snakeBlocks;
    }

    public boolean containsCell(Cell new_cell){
        for (Cell cell : this.getSnakeBlocks()){
            if (cell.getX() == new_cell.getX() && cell.getY() == new_cell.getY()){
                return true;
            }
        }
        return false;
    }

    public void eatFruit(Food fruit){
        this.length += fruit.getLength();
        this.point += fruit.getPoints();
        this.foodItems.add(fruit);
    }


    @Override
    public String toString() {
        return "Snake{" +
                "head=" + head +
                ", snakeBlocks=" + snakeBlocks +
                ", length=" + length +
                ", currentLength=" + currentLength +
                ", foodItems=" + foodItems +
                ", isAlive=" + isAlive +
                ", snakeDirection=" + snakeDirection +
                '}';
    }

}
