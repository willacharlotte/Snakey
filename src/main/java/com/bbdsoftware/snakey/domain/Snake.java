package com.bbdsoftware.snakey.domain;

import com.bbdsoftware.snakey.enums.Direction;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private Cell head;
    private final List<Cell> snakeBlocks = new ArrayList<>();
    private int length;
    private int currentLength = 1;
    private final List<Food> foodItems = new ArrayList<>();
    private boolean isAlive = true;
    private Direction snakeDirection = Direction.RIGHT;
    private float score = 0F;

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
     * @param newCell the new cell for snake to move to
     * @param direction the direction of the snake now
     */
    public void moveSnake(Cell newCell, Direction direction){
        if (this.isAlive){
            //System.out.println("snake moves " + direction + " to " + newCell);
            if (this.currentLength < this.length) {
                this.snakeBlocks.add(newCell);
                this.head = newCell;
                currentLength += 1;
            }
            else{
                this.snakeBlocks.remove(0);
                this.snakeBlocks.add(newCell);
                this.head = newCell;
            }
            this.snakeDirection = direction;
        }
    }

    /**
     * the snake eats a food
     * @param newCell the new cell to move to
     * @param direction the direction of movement
     * @param myFood the new food eaten
     */
    public void moveSnake(Cell newCell, Direction direction, Food myFood){
        if (this.isAlive){
            moveSnake(newCell, direction);
            eatFood(myFood);
        }
    }

    public void killSnake(){
        this.isAlive = false;
    }

    public boolean isValidTurn(Direction nextDirection){
        if (nextDirection.equals(this.snakeDirection)){
            return true;
        }
        else if ((nextDirection.equals(Direction.DOWN) || nextDirection.equals(Direction.UP)) &&
                (this.snakeDirection.equals(Direction.LEFT) || this.snakeDirection.equals(Direction.RIGHT))){
            return true;
        }
        else if ((nextDirection.equals(Direction.LEFT) || nextDirection.equals(Direction.RIGHT)) &&
                (this.snakeDirection.equals(Direction.DOWN) || this.snakeDirection.equals(Direction.UP))){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean containsCell(Cell newCell){
        for (Cell cell : this.getSnakeBlocks()){
            if (cell.getX() == newCell.getX() && cell.getY() == newCell.getY()){
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

    public void setSnakeDirection(Direction snakeDirection) {
        this.snakeDirection = snakeDirection;
    }

    public List<Cell> getSnakeBlocks() {
        return snakeBlocks;
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
