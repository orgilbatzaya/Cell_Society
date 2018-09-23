/**
 * @author yk154
 */

package Cell;

import Cell.Cell;
import Grid.Grid;
import java.util.List;
import java.util.Random;

public class FishCell extends Cell {
    public static final int WATER = 0;
    public static final int FISH = 1;
    public static final int SHARK = 2;
    List<Cell> waterNeig;

    public FishCell(int stateOne, int x, int y) {
        super(stateOne, stateOne, x, y);
    }


    @Override
    public void checkNeighbors(Grid g){
        for(var neighbor : getMyNeighbors()) {
            if(neighbor.getCurrentState() == WATER) {
                waterNeig.add(neighbor);
            }
        }

    }

    public void breed(Grid g){


    }
}

