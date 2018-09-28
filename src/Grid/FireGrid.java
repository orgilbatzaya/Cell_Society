/*
@author Amy Kim
 */

package Grid;

import Cell.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Grid for spreading of fire
 */
public class FireGrid extends Grid {
    private double prob;

    public FireGrid(int size, double prob){
        super(size);
        this.prob = prob;
        myCells = new ArrayList<ArrayList<Cell>>();
        initializeCells();
    }

    public void initializeCells(){
        for(int i = 0; i < size; i++){
            var row = new ArrayList<Cell>();
            for(int j = 0; j < size; j++){
                var random = new Random();
                var x = random.nextDouble();
                var y = 0;
                if(x < 0.1) y = FireCell.FIRE;
                else if (x <0.8) y = FireCell.TREE;
                else y = FireCell.GROUND;
                var cell = new FireCell(y, i, j, prob); //random states

                row.add(cell);
            }
            myCells.add(row);
        }
    }

    /**
     * Given some Cell in the Grid, finds and stores all (4) adjacent positions
     * regardless of whether these positions are bounded
     * @param cell Cell object
     * @return List of int arrays
     */
    @Override
    public List<int[]> getNearCellPositions(Cell cell) {
        List<int[]> positions = new ArrayList<>();
        int xPos = cell.getX();
        int yPos = cell.getY();

        if(inBounds(cell.getX(), cell.getY())){
            positions.add(new int[]{xPos -1, yPos});//sides
            positions.add(new int[]{xPos +1, yPos});
            positions.add(new int[]{xPos, yPos-1});
            positions.add(new int[]{xPos, yPos+1});
        }
        return positions;
    }

    /**
     *
     * @param cell
     *
     * it checks current State so that set Next State.
     */
    @Override
    public void checkNeighbors(Cell cell) {
        if(cell.getCurrentState() == FireCell.GROUND || cell.getCurrentState() == FireCell.FIRE) cell.setNextState(FireCell.GROUND);
        else {
            var cnt = 0; //counting how many neighbor Fire.
            for(var neighbor : getCellsNear(cell)) {
                if(neighbor.getCurrentState() == FireCell.FIRE) cnt ++;
            }

            if(cnt > 0) {
                var random = new Random();
                if(random.nextDouble() < prob) {
                    cell.setNextState(FireCell.FIRE); //if less that probability, next state will be fire
                    return;
                }
            } cell.setNextState(FireCell.TREE); //otherwise, it keeps tree state
        }
    }

    public void reset() {
        myCells.clear();
        initializeCells();
    }


}