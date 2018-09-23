package Grid;
import Cell.Cell;
import Cell.FishCell;
import Cell.SharkCell;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Collections;


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
        var states = randomizeStates(numWater,numShark,numShark);

        for(int i = 0; i < size; i++){
            var row = new ArrayList<FishCell>();
            for(int j = 0; j < size; j++){
                int state = (int) states.pop();
                var cell = new FishCell(state,state,i,j);
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
                cell.checkNeighbors(this);
                if(!cell.isSatisfied()){
                    swapRandomEmptyCell(cell);
                }
            }
        }
        updateStates();
        checkStats();
    }



}
