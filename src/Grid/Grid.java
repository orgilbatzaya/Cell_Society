/**
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
    protected ArrayList<ArrayList<Cell>> myCells;

    public Grid(int size) {
        this.size = size;
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




    public boolean inBounds(int x, int y) {
        if(x < 0 || x >= size){
            return false;
        }
        if(y < 0 || y >= size){
            return false;
        }
        return true;
    }

    public abstract List<int[]> getNearCellPositions(Cell cell);

    public abstract void updateEveryCell();
}