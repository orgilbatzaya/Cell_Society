/*
@author ob29, yk154
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
//    protected int getSize;
//    protected int setSize;
    protected List<List<Cell>> myCells;

    public Grid(int size, List<List<Cell>> cells) {
        this.size = size;
        myCells = cells;

    }

    public Cell getCellAt(int x, int y) {
        return myCells.get(x).get(y);
    }

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

    public List<int[]> getNearCellPositions(Cell cell){
        List<int[]> positions = new ArrayList<>();
        int xPos = cell.getX();
        int yPos = cell.getY();

        positions.add(new int[]{xPos -1, yPos});
        positions.add(new int[]{xPos +1, yPos});
        positions.add(new int[]{xPos, yPos-1});
        positions.add(new int[]{xPos, yPos+1});

        positions.add(new int[]{xPos-1, yPos-1});
        positions.add(new int[]{xPos-1, yPos+1});
        positions.add(new int[]{xPos+1, yPos-1});
        positions.add(new int[]{xPos+1, yPos+1});
        return positions;
    }


    public List<Cell> getCellsNear(Cell cell){
        List<Cell> nearCells = new ArrayList<Cell>();
        List<int[]> positions = getNearCellPositions(cell);
        for(int[] pos:positions){
            if(inBounds(pos[0], pos[1])){
                nearCells.add(myCells.get(pos[0]).get(pos[1]));
            }
        }


        return nearCells;
    }

    public boolean inBounds(int x, int y) {
        if(x < 0 || x >= size){
            return false;
        }
        if(y < 0 || y >= size){
            return false;
        }
        return true;
    }


//
//    public abstract List<Cell> getEmptyCellsNear();

    public abstract void updateEveryCell();

    public abstract double checkStats();


}