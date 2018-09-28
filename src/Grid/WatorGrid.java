/**
 * @author Amy Kim
 */

package Grid;
import Cell.Cell;

import java.util.*;

/**
 * WaTor Grid
 */
public class WatorGrid extends Grid {
    private int breedingTime;
    private int energy;

    public WatorGrid(int size, int breedingTime, int energy) {
        super(size);
        this.breedingTime = breedingTime;
        this.energy = energy;
        myCells = new ArrayList<ArrayList<Cell>>();
        initializeCells();
    }

    public void initializeCells(){
        for(int i = 0; i < size; i++){
            var row = new ArrayList<Cell>();
            for(int j = 0; j < size; j++){
                var random = new Random();
                var cell = new WaTorCell(random.nextInt(3), i, j, breedingTime, energy);
                row.add(cell);
            }
            myCells.add(row);
        }
    }

    public void reset() { //reset
        myCells.clear();
        initializeCells();
    }



    public List<Cell> getCellsNear(Cell cell){
        List<Cell> nearCells = new ArrayList<Cell>();
        List<int[]> positions = getNearCellPositions(cell);
        for(int[] pos:positions) {
            if (pos[0] < 0) {
                pos[0] = size - 1;
            }
            if (pos[0] >= size) {
                pos[0] = 0;
            }
            if (pos[1] < 0) {
                pos[1] = size - 1;
            }
            if (pos[1] >= size) {
                pos[1] = 0;
            }
            System.out.println(pos[0]);
            System.out.println(pos[1]);
            nearCells.add(myCells.get(pos[0]).get(pos[1]));
        }


        return nearCells;
    }

}