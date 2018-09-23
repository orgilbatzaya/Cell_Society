/*
@author yk154
 */

package Grid;

import Cell.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LifeGrid extends Grid {
    ArrayList<ArrayList<LifeCell>> myCells;

    public LifeGrid(int size){
        super(size);
        myCells = new ArrayList<ArrayList<LifeCell>>();
        initializeCells();
    }

    public void initializeCells(){
        for(int i = 0; i < size; i++){
            var row = new ArrayList<LifeCell>();
            for(int j = 0; j < size; j++){
                var random = new Random();
                var cell = new LifeCell(random.nextInt(2), i, j);
                row.add(cell);
            }
            myCells.add(row);
        }
    }

    public ArrayList<ArrayList<LifeCell>> getGrid(){
        return myCells;
    }


    public List<int[]> getNearCellPositions(Cell cell){
        List<int[]> positions = new ArrayList<>();
        int xPos = cell.getX();
        int yPos = cell.getY();

        if(inBounds(cell.getX(), cell.getY())){
            positions.add(new int[]{xPos -1, yPos});
            positions.add(new int[]{xPos +1, yPos});
            positions.add(new int[]{xPos, yPos-1});
            positions.add(new int[]{xPos, yPos+1});

            positions.add(new int[]{xPos-1, yPos-1});
            positions.add(new int[]{xPos-1, yPos+1});
            positions.add(new int[]{xPos+1, yPos-1});
            positions.add(new int[]{xPos+1, yPos+1});

        }

        return positions;
    }


    @Override
    public void updateEveryCell(){
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                var cell = myCells.get(x).get(y);
                cell.setCurrentState(cell.getNextState());
            }
        }
    }

}
