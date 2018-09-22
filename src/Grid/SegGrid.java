package Grid;

import Cell.Cell;
import Cell.SegregationCell;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

/*
@author ob29
 */

public class SegGrid extends Grid {
    public static final int RED = 1;
    public static final int BLUE = 2;
    public static final int EMPTY = 0;
    List<List<SegregationCell>> myCells;
    List<Cell> emptyCells;

    public SegGrid(int size,int similar, double rbRatio, double empty ){
        super(size);
        initializeCells(similar,rbRatio,empty);
    }

    public void initializeCells(int similar, double rbRatio, double empty){
        for(int i = 0; i < size; i++){
            var row = new ArrayList<SegregationCell>();
            for(int j = 0; j < size; j++){
                var cell = new SegregationCell(1,1,i,j,similar);
                row.add(cell);
            }
            myCells.add(row);
        }
    }


    /**
     * Called by SegregationCell cell in getNeighbors(), which is called
     * by checkNeighbors()
     * @param cell
     * @return list of near Cells that are inValidPosition 
     */
    public List<Cell> getCellsNear(Cell cell) {
        List<Cell> nearCells = new ArrayList<Cell>();
        List<int[]> nearCellPs = getNearCellPositions(cell);
        for(int[] pos:nearCellPs){
            if(isValidPosition(pos[0],pos[1])){
                nearCells.add(myCells.get(pos[0]).get(pos[1]));
            }
        }
        return nearCells;
    }

    public List<int[]> getNearCellPositions(Cell cell){
        List<int[]> nearCellPositions = new ArrayList<>();
        int xPos = cell.getX();
        int yPos = cell.getY();
        nearCellPositions.add(new int[]{xPos - 1, yPos});
        nearCellPositions.add(new int[]{xPos + 1, yPos});
        nearCellPositions.add(new int[]{xPos, yPos - 1});
        nearCellPositions.add(new int[]{xPos, yPos + 1});

        nearCellPositions.add(new int[]{xPos - 1, yPos - 1});
        nearCellPositions.add(new int[]{xPos - 1, yPos + 1});
        nearCellPositions.add(new int[]{xPos + 1, yPos - 1});
        nearCellPositions.add(new int[]{xPos + 1, yPos + 1});
        return nearCellPositions;
    }


    public boolean isValidPosition(int x, int y){
        if(x < 0 || x >= size){
            return false;
        }
        if(y < 0 || y >= size){
            return false;
        }
        return true;
    }

    public List<Cell> getEmptyCellsNear(){
        List<Cell> res = new ArrayList<>();
        return res;
    }

    /**
     * first passes through grid to find unsatisfied cells and setNextState
     * then passes again to actually update states
     */
    public  void updateEveryCell(){
        emptyCells = getEmptyCells(EMPTY);
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                var cell = myCells.get(x).get(y);
                cell.checkNeighbors(this);
                if(!cell.isSatisfied()){
                    swapRandomEmptyCell(cell);
                }
            }
        }
        updateStates();
        checkStats();
    }

    /**
     * turns next state into current one
     */
    public void updateStates(){
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                var cell = myCells.get(x).get(y);
                int nextState = cell.getNextState();
                cell.setCurrentState(nextState);
            }
        }
    }

    /**
     * swaps the next states of current cell and random empty cell
     * removes formerly empty cell from emptyCells
     * @param cell
     */

    public void swapRandomEmptyCell(Cell cell){
        Random random = new Random();
        int e = random.nextInt(size);
        cell.setNextState(EMPTY);
        emptyCells.get(e).setNextState(cell.getCurrentState());
        emptyCells.remove(e);
    }

    /**
     * calculates overall satisfaction of non-empty cells
     * @return double
     */
    public double checkStats() {
        int numSatisfied = 0;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if(myCells.get(x).get(y).isSatisfied()){
                    numSatisfied++;
                }
            }
        }
        return (1.0*numSatisfied)/(size*size);
    }



}
