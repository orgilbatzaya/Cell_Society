package Grid;

import Cell.Cell;
import Cell.SegregationCell;

import java.util.*;

/*
@author ob29
 */

public class SegGrid extends Grid {
    public static final int RED = 1;
    public static final int BLUE = 2;
    public static final int EMPTY = 0;
    List<List<SegregationCell>> myCells;
    List<Cell> emptyCells;

    public SegGrid(int size,int similar, double rbRatio, double empty ){
        super(size);
        initializeCells(similar,rbRatio,empty);
    }

    /**
     * Fills myCells with new Segregation Cells, with random placement of
     * red/blue/empty cells depending on User input, along with a
     * similarity/satisfaction threshold for all non-empty cells
     * @param similar
     * @param rbRatio percentage of red cells (user input)
     * @param empty percentage of empty cells (user input)
     */
    public void initializeCells(double similar, double rbRatio, double empty){
        int numEmpty = (int) empty*(size*size);
        int numRed = (int) rbRatio*(size*size - numEmpty);
        int numBlue = (size*size) - numEmpty - numRed;
        var states = randomizeStates(numEmpty,numRed,numBlue);

        for(int i = 0; i < size; i++){
            var row = new ArrayList<SegregationCell>();
            for(int j = 0; j < size; j++){
                int state = (int) states.pop();
                var cell = new SegregationCell(state,state,i,j,similar);
                row.add(cell);
            }
            myCells.add(row);
        }
    }

    private Stack randomizeStates(int empty, int red, int blue){
        Stack<Integer> bagOfStates = new Stack<>();
        for(int i = 0; i < empty; i++){
            bagOfStates.push(EMPTY);
        }
        for(int i = 0; i < red; i++){
            bagOfStates.push(RED);
        }
        for(int i = 0; i < blue; i++){
            bagOfStates.push(BLUE);
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

        nearCellPositions.add(new int[]{xPos - 1, yPos - 1});
        nearCellPositions.add(new int[]{xPos - 1, yPos + 1});
        nearCellPositions.add(new int[]{xPos + 1, yPos - 1});
        nearCellPositions.add(new int[]{xPos + 1, yPos + 1});
        return nearCellPositions;
    }




    public List<Cell> getEmptyCellsNear(){
        List<Cell> res = new ArrayList<>();
        return res;
    }

    /**
     * first passes through grid to find unsatisfied cells and setNextState
     * then passes again to actually update states
     */
    @Override
    public void updateEveryCell(){
        emptyCells = getEmptyCells(EMPTY);
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                var cell = myCells.get(x).get(y);
                cell.checkNeighbors(this);
                if(!cell.isSatisfied()){
                    swapRandomEmptyCell(cell);
                }
            }
        }
        super.updateEveryCell();
        checkStats();
    }

    /**
     * swaps the next states of current cell and random empty cell
     * removes formerly empty cell from emptyCells
     * @param cell
     */

    public void swapRandomEmptyCell(Cell cell){
        Random random = new Random();
        int e = random.nextInt(size);
        cell.setNextState(EMPTY);
        emptyCells.get(e).setNextState(cell.getCurrentState());
        emptyCells.remove(e);
    }

    /**
     * calculates overall satisfaction of non-empty cells
     * @return double
     */
    public double checkStats() {
        int numSatisfied = 0;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if(myCells.get(x).get(y).isSatisfied()){
                    numSatisfied++;
                }
            }
        }
        return (1.0*numSatisfied)/(size*size);
    }
}
