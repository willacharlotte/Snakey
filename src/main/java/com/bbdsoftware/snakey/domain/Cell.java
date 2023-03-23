package com.bbdsoftware.snakey.domain;

import com.bbdsoftware.snakey.enums.CellType;
import com.bbdsoftware.snakey.enums.Direction;

public class Cell{
    private int x;
    private int y;
    private CellType cellType;
    private Direction cellDirection;

    public Cell(){}

    public Cell(int x, int y, Direction cellDirection){
        this.x = x;
        this.y = y;
        this.cellType = CellType.NONE;
        this.cellDirection = cellDirection;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setCellType(CellType cellType){
        this.cellType = cellType;
    }

    public void setCellDirection(Direction cellDirection){
        this.cellDirection = cellDirection;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public CellType getCellType() {
        return this.cellType;
    }

    public Direction getCellDirection() {
        return this.cellDirection;
    }
    
    public String toString(){
        return "(" + this.x + ", " + this.y + ", " + this.cellType + ")";
    }
}