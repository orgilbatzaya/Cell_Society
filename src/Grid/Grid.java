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
    protected List<List<Cell>> myCells;

    public Grid(int size) {
        this.size = size;
    }

    protected List<Cell> getEmptyCells(int emptyVal){ //get all the empty cells
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


    protected List<Cell> getCellsNear(Cell cell){
        List<Cell> nearCells = new ArrayList<Cell>();
        List<int[]> positions = getNearCellPositions(cell);
        for(int[] pos:positions){
            if(inBounds(pos[0], pos[1])){
                nearCells.add(myCells.get(pos[0]).get(pos[1]));
            }
        }
        return nearCells;
    }

    protected boolean inBounds(int x, int y) {
        if(x < 0 || x >= size){
            return false;
        }
        if(y < 0 || y >= size){
            return false;
        }
        return true;
    }
    public List<List<Cell>> getGrid(){
        return myCells;
    }

    protected abstract List<int[]> getNearCellPositions(Cell cell);

    protected void updateEveryCell() {
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                var cell = myCells.get(x).get(y);
                cell.setCurrentState(cell.getNextState());
            }
        }
    }
}