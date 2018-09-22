/*
@author yk154
 */

package Grid;

import Cell.*;

import java.util.List;
import java.util.ArrayList;

public class FireGrid extends Grid {
    public FireGrid(int size, List<List<Cell>> cells){
        super(size, cells);
    }

    @Override
    public List<int[]> getNearCellPositions(Cell cell) {
        return null;
    }

    @Override
    public void updateEveryCell(){



    }

    @Override
    public double checkStats(){
        double prob = 0;






        return prob;
    }

}
