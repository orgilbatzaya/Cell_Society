/*
@author Amy Kim
 */

package Grid;

import Cell.*;

import java.util.List;
import java.util.ArrayList;
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
                var cell = new FireCell(random.nextInt(3), i, j, prob); //random states
                row.add(cell);
            }
            myCells.add(row);
        }
    }

    public void reset() {
        myCells.clear();
        initializeCells();
    }


}