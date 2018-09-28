/**
 * @author Amy Kim
 */

package Grid;
import Cell.Cell;
import Cell.SharkCell;
import Cell.FishCell;

import java.util.*;

/**
 * WaTor Grid
 */
public class WatorGrid extends Grid {
    private int energy;
    private int breedingTime;
    private double sharkRatio;
    private double emptyRatio;
    public static final int SHARK = 2;
    public static final int FISH = 1;
    public static final int WATER = 0;
    public static final int DEFAULT = 0;

    public WatorGrid(int size, double sharkRatio, double emptyRatio, int breedingTime, int sharkEnergy) {
        super(size);
        this.energy = sharkEnergy;
        this.breedingTime = breedingTime;
        this.sharkRatio = sharkRatio;
        this.emptyRatio = emptyRatio;
        myCells = new ArrayList<ArrayList<Cell>>();
        initializeCells();
    }

    public void initializeCells(){
        var states = randomizeStates();

        for(int i = 0; i < size; i++){
            var row = new ArrayList<Cell>();
            for(int j = 0; j < size; j++){
                Cell cell;
                int state = states.pop();
                if(state == SHARK) {
                    cell = new SharkCell(state, state, i, j, breedingTime, energy);
                } else { //state is either fish or water
                    cell = new FishCell(state, state, i, j, breedingTime);
                }
                row.add(cell);
            }
            myCells.add(row);
        }
    }


    public Stack<Integer> randomizeStates(){
        var animals = new Stack<Integer>();
        int numEmpty = (int) (emptyRatio*size*size);
        int numShark = (int) (sharkRatio*(size*size - numEmpty));
        int numFish = (size*size) - numEmpty - numShark;
        for(int i = 0; i < numEmpty; i++){
            animals.add(WATER);
        }
        for(int i = 0; i < numShark; i++){
            animals.add(SHARK);
        }
        for(int i = 0; i < numFish; i++){
            animals.add(FISH);
        }
        Collections.shuffle(animals);
        return animals;
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

    /**
     * first passes through grid to find unsatisfied cells and setNextState
     * then passes again to actually update states
     */
    @Override
    public  void updateEveryCell(){

        var aliveCells = getAliveCells();
        Collections.shuffle(aliveCells);

        for(Cell cell: aliveCells){
            if(cell.getCurrentState() == SHARK){
                ((SharkCell) cell).move();
            } else {
                ((FishCell) cell).move();
            }
        }
        updateStates();
    }

    public List<Cell> getAliveCells(){
        var alive = new ArrayList<Cell>();
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                var cell = myCells.get(i).get(j);
                if(cell.getCurrentState() != WATER){
                    alive.add(cell);
                }
            }
        }
        return alive;
    }

    /**
     * turns next state into current one
     */
    public void updateStates(){
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                var cell = myCells.get(x).get(y);
                int nextState = cell.getNextState();
                cell.setCurrentState(nextState);
            }
        }
    }

}