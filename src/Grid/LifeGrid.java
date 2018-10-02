/*
@author Amy Kim
 */

package Grid;

import Cell.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * sub grid class for life of game
 */
public class LifeGrid extends Grid {

    public LifeGrid(int size){
        super(size);
        myCells = new ArrayList<ArrayList<Cell>>();
        initializeCells();
    }

    public void initializeCells(){
        for(int i = 0; i < size; i++){
            var row = new ArrayList<Cell>();
            for(int j = 0; j < size; j++){
                var random = new Random();
                var cell = new LifeCell(random.nextInt(2), i, j); //random state
                row.add(cell);
            }
            myCells.add(row);
        }
    }

    /**
     *
     * @param cell LifeGrid
     * it checks current State so that set Next State.
     */
    @Override
    public void checkNeighbors(Cell cell) {
        var cnt = 0;
        this.getCellsNear(cell);
        for(var neighbor : getCellsNear(cell)) {
            if(neighbor.getCurrentState() == LifeCell.ALIVE) cnt ++; //counting how many alive
        }

        if(cell.getCurrentState() == LifeCell.ALIVE) { //set next state for alive cells
            if(cnt < 2) cell.setNextState(LifeCell.DEAD); //if neighbor which alives less than 2, it will dead
            else if(cnt == 2 || cnt == 3) cell.setNextState(LifeCell.ALIVE);
            else cell.setNextState(LifeCell.DEAD); // Otherwise, dead
        } else {
            if(cnt == 3) cell.setNextState(LifeCell.ALIVE);
        }
    }

    @Override
    public double[] getStats(){
        int L, D;
        L = D = 0;
        for(int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (myCells.get(x).get(y).getCurrentState() == LifeCell.ALIVE) {
                    L++;
                } else {
                    D++;
                }
            }
        }
        return new double[]{(double)L/(size*size),(double)D/(size*size)};
    }
}
