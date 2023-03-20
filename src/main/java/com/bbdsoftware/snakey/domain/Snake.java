package com.bbdsoftware.snakey.domain;

import com.bbdsoftware.snakey.enums.Direction;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private Cell head;
    //private Cell[] snakeBlocks;
    private final List<Cell> snakeBlocks = new ArrayList<>();
    private int length;
    private int currentLength = 1;
    private List<Food> foodItems = new ArrayList<>();
    private boolean isAlive = true;
    private Direction snakeDirection;
    private float score = 0.1F;

    public Snake(){
        this.length = 1;
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

    /**
     * the snake eats a food
     * @param new_cell the new cell to move to
     * @param direction the direction of movement
     * @param my_food the new food eaten
     */
    public void moveSnake(Cell new_cell, Direction direction, Food my_food){
        if (this.isAlive){
            moveSnake(new_cell, direction);
            eatFood(my_food);
        }
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

    public void eatFood(Food food){
        System.out.println("eatFood: " + food);
        this.length += food.getLength();
        this.score += food.getScore();
        this.foodItems.add(food);
    }

    public int getLength() {
        return length;
    }

    public int getCurrentLength() {
        return currentLength;
    }

    public List<Food> getFoodItems() {
        return foodItems;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public Direction getSnakeDirection() {
        return snakeDirection;
    }

    public float getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Snake{" +
                "head=" + head +
                ", \nsnakeBlocks=" + snakeBlocks +
                ", \nlength=" + length +
                ", currentLength=" + currentLength +
                ", \nfoodItems=" + foodItems +
                ", \nisAlive=" + isAlive +
                ", snakeDirection=" + snakeDirection +
                '}';
    }

}