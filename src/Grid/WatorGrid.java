package Grid;
import Cell.Cell;
import Cell.FishCell;
import Cell.SharkCell;

import java.util.*;


public class WatorGrid extends Grid {
    public static final int SHARK = 2;
    public static final int FISH = 1;
    public static final int WATER = 0;
    List<List<FishCell>> myCells;
    List<Cell> emptyCells;

    public WatorGrid(int size) {
       super(size);

    }

    public void initializeCells(double sharkRatio, double empty){
        int numWater = (int) empty*(size*size);
        int numShark = (int) sharkRatio*(size*size - numWater);
        int numFish = (size*size) - numWater - numShark;
        var states = randomizeStates(numWater,numShark,numFish);

        for(int i = 0; i < size; i++){
            var row = new ArrayList<FishCell>();
            for(int j = 0; j < size; j++){
                int state = (int) states.pop();
                var cell = new FishCell(state,state,i,j);//can be empty or fish
                if(state == SHARK){
                    cell = new SharkCell(state,state,i,j);
                }
                row.add(cell);
            }
            myCells.add(row);
        }
    }
    private Stack randomizeStates(int empty, int red, int blue){
        Stack<Integer> bagOfStates = new Stack<>();
        for(int i = 0; i < empty; i++){
            bagOfStates.push(WATER);
        }
        for(int i = 0; i < red; i++){
            bagOfStates.push(SHARK);
        }
        for(int i = 0; i < blue; i++){
            bagOfStates.push(FISH);
        }
        Collections.shuffle(bagOfStates);
        return bagOfStates;
    }

    public List<Cell> getCellsNear(Cell cell){
        List<Cell> nearCells = new ArrayList<Cell>();
        List<int[]> positions = getNearCellPositions(cell);
        for(int[] pos:positions){
            if(inBounds(pos[0], pos[1])){
                nearCells.add(myCells.get(pos[0]).get(pos[1]));
            }
        }
        return nearCells;
    }

    public List<int[]> getNearCellPositions(Cell cell){
        List<int[]> nearCellPositions = new ArrayList<>();
        int xPos = cell.getX();
        int yPos = cell.getY();
        nearCellPositions.add(new int[]{xPos - 1, yPos});
        nearCellPositions.add(new int[]{xPos + 1, yPos});
        nearCellPositions.add(new int[]{xPos, yPos - 1});
        nearCellPositions.add(new int[]{xPos, yPos + 1});
        return nearCellPositions;
    }

    public void updateEveryCell(){
        emptyCells = getEmptyCells(WATER);
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                var cell = myCells.get(x).get(y);
                if(cell instanceof FishCell){
                    move(cell);
                }
                else if (cell instanceof SharkCell){
                    move(cell);
                }
                cell.checkNeighbors(this);

            }
        }
        updateStates();
        checkStats();
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

    /**
     * swaps the next states of current cell and random empty cell
     * removes formerly empty cell from emptyCells
     * @param cell
     */

    public void move(FishCell cell){
        List<Cell> tempNeighbors;
        Random random = new Random();
        tempNeighbors = cell.getMyNeighbors();
        int neighborSize = tempNeighbors.size();

        if(neighborSize >= 1){
            moveToAdjacentWater(cell, tempNeighbors);
        }

        cell.lowerBreedingTime();
        breed(cell, tempNeighbors);
    }

    public void move(SharkCell cell){
        List<Cell> tempNeighbors;
        Random random = new Random();
        tempNeighbors = cell.getMyNeighbors();
        int neighborSize = tempNeighbors.size();
        if(cell.checkFishNear()){
            int f = random.nextInt(neighborSize);
            cell.setNextState(WATER);
            tempNeighbors.get(f)
            tempNeighbors.get(f).setNextState(cell.getCurrentState());
            tempNeighbors.remove(f);
        }
        if(neighborSize >= 1) {
            moveToAdjacentWater(cell, tempNeighbors);
        }
        cell.lowerBreedingTime();
        breed(cell, tempNeighbors);

    }

    public void breed(FishCell cell, List<Cell> tempNeighbors){
        Random random = new Random();
        if(cell.checkBreeding() && tempNeighbors.size() >= 1){
            int b = random.nextInt(tempNeighbors.size());
            tempNeighbors.get(b).setNextState(cell.getCurrentState());
            tempNeighbors.remove(b);
        }
    }

    public void moveToAdjacentWater(FishCell cell, List<Cell> tempNeighbors){
        Random random = new Random();
        int w = random.nextInt(tempNeighbors.size());
        cell.setNextState(WATER);
        tempNeighbors.get(w).setNextState(cell.getCurrentState());
        tempNeighbors.remove(w);
    }


    public void eatFish() {
        int f = random.nextInt(neighborSize);
        cell.setNextState(WATER);
        tempNeighbors.get(f)
        tempNeighbors.get(f).setNextState(cell.getCurrentState());
        tempNeighbors.remove(f);
    }



}
