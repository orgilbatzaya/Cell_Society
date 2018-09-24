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
    ArrayList<ArrayList<SegregationCell>> myCells = new ArrayList<ArrayList<SegregationCell>>();
    List<SegregationCell> emptyCells;
    int similar;
    double rbRatio;
    double empty;

    public SegGrid(int size, int similar, double rbRatio, double empty ){
        super(size);
        this.similar = similar;
        this.rbRatio = rbRatio;
        this.empty = empty;
        initializeCells(similar,rbRatio,empty);
    }

    /**
     * Fills myFireCells with new Segregation Cells, with random placement of
     * red/blue/empty cells depending on User input, along with a
     * similarity/satisfaction threshold for all non-empty cells
     * @param similar
     * @param rbRatio percentage of red cells (user input)
     * @param empty percentage of empty cells (user input)
     */
    public void initializeCells(double similar, double rbRatio, double empty){
        int numEmpty = (int) (empty*size*size);
        int numRed = (int) (rbRatio*(size*size - numEmpty));
        int numBlue = (size*size) - numEmpty - numRed;
        var states = randomizeStates(numEmpty,numRed,numBlue);

        for(int i = 0; i < size; i++){
            var row = new ArrayList<Cell>();
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
            bagOfStates.add(EMPTY);
        }
        for(int i = 0; i < red; i++){
            bagOfStates.add(RED);
        }
        for(int i = 0; i < blue; i++){
            bagOfStates.add(BLUE);
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

        nearCellPositions.add(new int[]{xPos - 1, yPos - 1});
        nearCellPositions.add(new int[]{xPos - 1, yPos + 1});
        nearCellPositions.add(new int[]{xPos + 1, yPos - 1});
        nearCellPositions.add(new int[]{xPos + 1, yPos + 1});
        return nearCellPositions;
    }






    /**
     * first passes through grid to find unsatisfied cells and setNextState
     * then passes again to actually update states
     */
    public  void updateEveryCell(){
        emptyCells = getEmptyCells(EMPTY);
        for(SegregationCell t: emptyCells){
            t.unTaken();
        }
        Random random = new Random();
        List<SegregationCell> unsatisfied = getUnsatisfiedCells();

        Collections.shuffle(unsatisfied);
        for(SegregationCell cell: unsatisfied){
            swapRandomEmptyCell(cell);
        }


        updateStates();
        checkStats();
    }

    public List<SegregationCell> getUnsatisfiedCells() {
        List<SegregationCell> unsatisfied = new ArrayList<>();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                var cell = myCells.get(x).get(y);
                if (cell.getCurrentState() != EMPTY) {
                    cell.getNeighbors(this);
                    cell.checkNeighbors(this);
                    if(!cell.isSatisfied()){
                        unsatisfied.add(cell);
                    }
                }
                cell.clearNeighbors();
            }
        }
        return unsatisfied;
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

    public void swapRandomEmptyCell(SegregationCell cell){
        Random random = new Random();
        int emptySize = emptyCells.size();
        int cnt = 0;
        for(SegregationCell c: emptyCells){
            if(!c.checkTaken()){
                cnt++;
            }
        }
        if(emptySize > 0 && cnt > 0) {
            int e = random.nextInt(emptySize);
            cell.setNextState(EMPTY);
            emptyCells.get(e).setNextState(cell.getCurrentState());
            emptyCells.get(e).setTaken();
            emptyCells.remove(e);
        }
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

    public ArrayList<ArrayList<SegregationCell>> getGrid(){
        return myCells;
    }

    public void reset() {
        myCells.clear();
        initializeCells(similar,rbRatio,empty);
    }

    public List<SegregationCell> getEmptyCells(int emptyVal){ //get all the empty cells
        List<SegregationCell> requiredCells = new ArrayList<>();
        for(ArrayList<SegregationCell> row: myCells){
            for(SegregationCell c: row){
                if(c.getCurrentState() == emptyVal){
                    requiredCells.add(c);
                }
            }
        }
        return requiredCells;
    }



}
