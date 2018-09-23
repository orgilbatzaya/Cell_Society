/*
@author Amy Kim
 */

package Grid;

import Cell.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LifeGrid extends Grid {
    ArrayList<ArrayList<LifeCell>> myLifeCells;

    public LifeGrid(int size){
        super(size);
        myLifeCells = new ArrayList<ArrayList<LifeCell>>();
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
            myLifeCells.add(row);
        }
    }

    public ArrayList<ArrayList<LifeCell>> getGrid(){
        return myLifeCells;
    }

    public void reset() {
        myLifeCells.clear();
        initializeCells();
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

    public List<Cell> getCellsNear(Cell cell){
        List<Cell> nearCells = new ArrayList<Cell>();
        List<int[]> positions = getNearCellPositions(cell);
        for(int[] pos:positions){
            if(inBounds(pos[0], pos[1])){
                nearCells.add(myLifeCells.get(pos[0]).get(pos[1]));
            }
        }
        return nearCells;
    }

    @Override
    public void updateEveryCell(){
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                var cell = myLifeCells.get(x).get(y);
                cell.checkNeighbors(this);
                cell.setCurrentState(cell.getNextState());
            }
        }
    }
}
