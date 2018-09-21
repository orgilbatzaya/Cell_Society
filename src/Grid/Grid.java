/*
@author ob29
 */
package Grid;

import Cell.Cell;

import java.util.ArrayList;
import java.util.List;
/**
 *
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

    public List<Cell> getCellsNear(Cell cell) {
        int xPos = cell.getX();
        int yPos = cell.getY();
        List<Cell> cellsNear = new ArrayList<Cell>();
        //adjacent
        cellsNear.add(myCells.get(xPos - 1).get(yPos));
        cellsNear.add(myCells.get(xPos + 1).get(yPos));
        cellsNear.add(myCells.get(xPos).get(yPos - 1));
        cellsNear.add(myCells.get(xPos).get(yPos + 1));
        //diagonals
        cellsNear.add(myCells.get(xPos - 1).get(yPos - 1));
        cellsNear.add(myCells.get(xPos - 1).get(yPos + 1));
        cellsNear.add(myCells.get(xPos + 1).get(yPos - 1));
        cellsNear.add(myCells.get(xPos + 1).get(yPos + 1));

        return cellsNear;
    }

    public List<Cell> getEmptyCells(int emptyVal){
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
}