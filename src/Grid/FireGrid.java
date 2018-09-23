/*
@author yk154
 */

package Grid;

import Cell.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class FireGrid extends Grid {
    List<List<FireCell>> myCells;
    private double prob; // will be changed

    public FireGrid(int size, int x, int y, double prob){
        super(size);
        initializeCells(x,y);
        this.prob = prob;
    }

    public void initializeCells(int x, int y){
        for(int i = 0; i < size; i++){
            var row = new ArrayList<FireCell>();
            for(int j = 0; j < size; j++){
                var random = new Random();
                var cell = new FireCell(random.nextInt(3), x, y, prob);
                row.add(cell);
            }
            myCells.add(row);
        }
    }

    @Override
    public List<int[]> getNearCellPositions(Cell cell) {
        List<int[]> positions = new ArrayList<>();
        int xPos = cell.getX();
        int yPos = cell.getY();

        if(inBounds(cell.getX(), cell.getY())){
            positions.add(new int[]{xPos -1, yPos});
            positions.add(new int[]{xPos +1, yPos});
            positions.add(new int[]{xPos, yPos-1});
            positions.add(new int[]{xPos, yPos+1});
        }

        return positions;
    }
}