package com.bbdsoftware.snakey.domain;
import com.bbdsoftware.snakey.enums.Direction;
import com.bbdsoftware.snakey.enums.FoodTypes;

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
    private int score = 0;

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

    public Cell moveSnake(Direction direction){
        Cell head = this.head;
        int x = head.getX(), y = head.getY();
        if (this.snakeDirection.equals(direction) && direction.equals(Direction.UP)){
            y -= 1;
        }
        else if (this.snakeDirection.equals(direction) && direction.equals(Direction.DOWN)){
            y += 1;
        }
        else if (this.snakeDirection.equals(direction) && direction.equals(Direction.LEFT)){
            x -= 1;
        }
        else if (this.snakeDirection.equals(direction) && direction.equals(Direction.RIGHT)){
            x += 1;
        }
        else if (this.snakeDirection.equals(Direction.UP) && direction.equals(Direction.LEFT)){
            x -= 1;
        }
        else if (this.snakeDirection.equals(Direction.UP) && direction.equals(Direction.RIGHT)){
            x += 1;
        }
        else if (this.snakeDirection.equals(Direction.DOWN) && direction.equals(Direction.LEFT)){
            x -= 1;
        }
        else if (this.snakeDirection.equals(Direction.DOWN) && direction.equals(Direction.RIGHT)){
            x += 1;
        }
        else if (this.snakeDirection.equals(Direction.RIGHT) && direction.equals(Direction.UP)){
            y -= 1;
        }
        else if (this.snakeDirection.equals(Direction.RIGHT) && direction.equals(Direction.DOWN)){
            y += 1;
        }
        else if (this.snakeDirection.equals(Direction.LEFT) && direction.equals(Direction.UP)){
            y -= 1;
        }
        else if (this.snakeDirection.equals(Direction.LEFT) && direction.equals(Direction.DOWN)){
            y += 1;
        }
        this.snakeDirection = direction;
        return new Cell(x, y, direction);
    }

    /**
     * moves the snake and sets its direction
     * @param newCell the new cell for snake to move to
     */
    public void moveSnake(Cell newCell){
        if (this.isAlive){
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
        }
    }

    /**
     * the snake eats a food
     * @param newCell the new cell to move to
     * @param myFood the new food eaten
     */
    public void moveSnake(Cell newCell, Food myFood){
        if (this.isAlive){
            moveSnake(newCell);
            eatFood(myFood);
        }
    }

    public void killSnake(){
        this.isAlive = false;
    }

    public boolean isValidTurn(Direction nextDirection){
        if(currentLength == 1){
            return true;
        }
        else if (nextDirection.equals(this.snakeDirection)){
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

        food.doSpecial(this);
    }

    /**
     * get count of the foodItem
     * @param foodType the foodItem to get the count of
     * @return the count of the foodItem
     */
    public int getFoodItemCount(FoodTypes foodType){
        int countFood = 0;
        for (Food food : foodItems){
            if (food.getFoodType().equals(foodType))
                countFood++;
        }
        return countFood;
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

    public int getScore() {
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
