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

    /**
     * Given some Cell in the Grid, finds and stores all (4) adjacent positions
     * regardless of whether these positions are bounded
     * @param cell Cell object
     * @return List of int arrays
     */
    @Override
    public List<int[]> getNearCellPositions(Cell cell) {
        List<int[]> positions = new ArrayList<>();
        int xPos = cell.getX();
        int yPos = cell.getY();

        if(inBounds(cell.getX(), cell.getY())){
            positions.add(new int[]{xPos -1, yPos});//sides
            positions.add(new int[]{xPos +1, yPos});
            positions.add(new int[]{xPos, yPos-1});
            positions.add(new int[]{xPos, yPos+1});
        }
        return positions;
    }

    @Override
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
        for(ArrayList<Cell> row: myCells){
            for(Cell t:row){
                t.unTaken(); //at each step, "untake" taken cells
            }
        }
        var sharkCells = getRequiredCells(SHARK);
        System.out.println(sharkCells.size());
        var FishCells = getRequiredCells(FISH);
        Collections.shuffle(sharkCells);
        Collections.shuffle(sharkCells);
        for(Cell cell: sharkCells) {
            cell.getNeighbors(this);
            ((SharkCell) cell).move();
            if (cell.isMoving()) {
                reposition(cell);
            }
            cell.unMoving();
            //System.out.println(((SharkCell) cell).getEnergy());
            if(cell.isBirthing()){
                birth(cell);
            }
            cell.unBirthing();
            cell.clearNeighbors();
        }
        for(Cell cell: FishCells){
            cell.getNeighbors(this);
            ((FishCell) cell).move();
            if(cell.isMoving()){
                reposition(cell);
            }
            cell.unMoving();

            if(cell.isBirthing()){
                birth(cell);
            }
            cell.unBirthing();
            cell.clearNeighbors();
        }
        updateStates();
        int cnt = 0;
        for(int x = 0; x < size; x++){
            for(int y = 0; y <size; y++){
                if(myCells.get(x).get(y).getCurrentState() == FISH){
                    cnt++;
                }
            }
        }
        System.out.println("fishcount" + cnt);


    }

    public void reposition(Cell cell){
        int[] newPos;
        if(cell.getCurrentState() == SHARK) {
            newPos = ((SharkCell) cell).getNextPos();
        } else{
            newPos = ((FishCell) cell).getNextPos();
        }
        var next = myCells.get(newPos[0]).get(newPos[1]);

        Cell replacement;
        if(cell.getCurrentState() == SHARK) {
            replacement = new SharkCell(SHARK, SHARK, next.getX(), next.getY(), ((SharkCell) cell).getBreedingTime(), ((SharkCell) cell).getEnergy());
        }
        else{
            replacement = new FishCell(FISH, FISH, next.getX(), next.getY(), ((FishCell) cell).getBreedingTime());

        }
        myCells.get(newPos[0]).set(newPos[1], replacement);

    }

    public void birth(Cell cell){
        int[] babyPos;
        if(cell.getCurrentState() == SHARK) {
            babyPos = ((SharkCell) cell).getBabyPos();
            ((SharkCell) cell).resetEnergyAndBreed();
        } else{
            babyPos = ((FishCell) cell).getBabyPos();
            ((FishCell) cell).resetEnergyAndBreed();
        }
        var next = myCells.get(babyPos[0]).get(babyPos[1]);

        Cell replacement;
        if(cell.getCurrentState() == SHARK) {
            replacement = new SharkCell(SHARK, SHARK, next.getX(), next.getY(), breedingTime, energy);
        }
        else{
            replacement = new FishCell(FISH, FISH, next.getX(), next.getY(), breedingTime);

        }
        myCells.get(babyPos[0]).set(babyPos[1], replacement);
    }



    public List<Cell> getRequiredCells(int state){
        var required = new ArrayList<Cell>();
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                var cell = myCells.get(i).get(j);
                if(cell.getCurrentState() == state){
                    required.add(cell);
                }
            }
        }
        return required;
    }





}