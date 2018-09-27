/*
@author Amy Kim
 */

package Grid;

import Cell.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * grid for life of game
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


    public void reset() {
        myCells.clear();
        initializeCells();
    }
}