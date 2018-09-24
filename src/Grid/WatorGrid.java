/**
 * @author Amy Kim
 */

package Grid;
import Cell.Cell;
import Cell.WaTorCell;

import java.lang.reflect.Array;
import java.util.*;


public class WatorGrid extends Grid {
    ArrayList<ArrayList<WaTorCell>> myWaTorCells;
    private int breedingTime;
    private int energy;

    public WatorGrid(int size, int breedingTime, int energy) {
       super(size);
       this.breedingTime = breedingTime;
       this.energy = energy;
       myWaTorCells = new ArrayList<ArrayList<WaTorCell>>();
       initializeCells();
    }

    public void initializeCells(){
        for(int i = 0; i < size; i++){
            var row = new ArrayList<WaTorCell>();
            for(int j = 0; j < size; j++){
                var random = new Random();
                var cell = new WaTorCell(random.nextInt(3), i, j, breedingTime, energy);
                row.add(cell);
            }
            myWaTorCells.add(row);
        }
    }

    public ArrayList<ArrayList<WaTorCell>> getGrid(){
        return myWaTorCells;
    }

    public void reset() {
        myWaTorCells.clear();
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
    public void updateEveryCell() {
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                var cell = myWaTorCells.get(x).get(y);
                cell.checkNeighbors(this);
                cell.setCurrentState(cell.getNextState());
            }
        }
    }


    public List<Cell> getCellsNear(Cell cell){
        List<Cell> nearCells = new ArrayList<Cell>();
        List<int[]> positions = getNearCellPositions(cell);
        for(int[] pos:positions){
            if(inBounds(pos[0], pos[1])){
                nearCells.add(myWaTorCells.get(pos[0]).get(pos[1]));
            }
            else{ //Cell of outBounds goes to opposite side.
                //Two if statements to find which is out bound btw X or Y.
                if(pos[0] < 0) nearCells.add(myWaTorCells.get(size-1).get(pos[1]));
                else if(pos[0] >= size) nearCells.add(myWaTorCells.get(0).get(pos[1]));
                else if(pos[1] < 0) nearCells.add(myWaTorCells.get(pos[0]).get(size-1));
                else if(pos[1] >= size) nearCells.add(myWaTorCells.get(pos[0]).get(0));
            }
        }
        return nearCells;
    }

}
