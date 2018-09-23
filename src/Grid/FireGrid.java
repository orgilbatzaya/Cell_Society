/*
@author Amy Kim
 */

package Grid;

import Cell.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class FireGrid extends Grid {
    ArrayList<ArrayList<FireCell>> myFireCells;
    private double prob;

    public FireGrid(int size, double prob){
        super(size);
        this.prob = prob;
        myFireCells = new ArrayList<ArrayList<FireCell>>();
        initializeCells();
    }

    public void initializeCells(){
        for(int i = 0; i < size; i++){
            var row = new ArrayList<FireCell>();
            for(int j = 0; j < size; j++){
                var random = new Random();
                var cell = new FireCell(random.nextInt(3), i, j, prob);
                row.add(cell);
            }
            myFireCells.add(row);
        }
    }


    public ArrayList<ArrayList<FireCell>> getGrid(){
        return myFireCells;
    }

    public void reset() {
        myFireCells.clear();
        initializeCells();
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

    @Override
    public void updateEveryCell(){
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                var cell = myFireCells.get(x).get(y);
                cell.setCurrentState(cell.getNextState());
            }
        }
    }

    public List<Cell> getCellsNear(Cell cell){
        List<Cell> nearCells = new ArrayList<Cell>();
        List<int[]> positions = getNearCellPositions(cell);
        for(int[] pos:positions){
            if(inBounds(pos[0], pos[1])){
                nearCells.add(myFireCells.get(pos[0]).get(pos[1]));
            }
        }
        return nearCells;
    }
}