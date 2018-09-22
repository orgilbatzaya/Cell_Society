/*
@author ob29, yk154
 */
package Grid;

import Cell.Cell;

import java.util.ArrayList;
import java.util.List;
/**
 * isbound - grid ,
 */
public abstract class Grid {
    protected int size;
    protected int getSize;
    protected int setSize;
    protected List<List<Cell>> myCells;

    public Grid(int size, List<List<Cell>> cells) {
        this.size = size;
        myCells = cells;

    }

    public Cell getCellAt(int x, int y) {
        return myCells.get(x).get(y);
    }

    public abstract List<Cell> getCellsNear(Cell cell);

    public List<Cell> getEmptyCells(int emptyVal){ //get all the empty cells
        List<Cell> requiredCells = new ArrayList<>();
        for(List<Cell> row: myCells){
            for(Cell c: row){
                if(c.getCurrentState() == emptyVal){
                    requiredCells.add(c);
                }
            }
        }
        return requiredCells;
    }

    public abstract List<Cell> getEmptyCellsNear();

    public abstract void updateEveryCell();

    public abstract int checkStats();

    /**
     * Fire, Life of Game and segregation cell in bounds
     */
    public boolean inBounds(int x, int y) {
        for(List<Cell> row: myCells){
            for(Cell c: row){
                if(c.getX() <0 || c.getX() >= size) return false;
                if(c.getY() < 0 || c.getY() >= size) return false;
            }
        }
        return true;
    }
}