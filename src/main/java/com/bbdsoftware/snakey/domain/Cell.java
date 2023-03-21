package com.bbdsoftware.snakey.domain;

import com.bbdsoftware.snakey.enums.CellType;

public class Cell{
    private int x;
    private int y;
    private CellType cellType;

    public Cell(){}

    public Cell(int x, int y){
        this.x = x;
        this.y = y;
        this.cellType = CellType.NONE;
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

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public CellType getCellType() {
        return cellType;
    }

    public String toString(){
        //return "x: " + this.x + ", y: " + this.y;
        return "(" + this.x + ", " + this.y + ", " + this.cellType + ")";
    }
}