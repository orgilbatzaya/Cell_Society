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
    List<Cell> emptyCells;
    int similar;
    double rbRatio;
    double empty;

    public SegGrid(int size, int similar, double rbRatio, double empty ){
        super(size);
        myCells = new ArrayList<ArrayList<Cell>>();
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


    /**
     * first passes through grid to find unsatisfied cells and setNextState
     * then passes again to actually update states
     */
    @Override
    public  void updateEveryCell(){
        emptyCells = getEmptyCells(EMPTY);
        for(Cell t: emptyCells){
            t.unTaken();
        }
        Random random = new Random();
        List<Cell> unsatisfied = getUnsatisfiedCells();

        Collections.shuffle(unsatisfied);
        for(Cell cell: unsatisfied){
            swapRandomEmptyCell(cell);
        }


        updateStates();
        checkStats();
    }

    public List<Cell> getUnsatisfiedCells() {
        List<Cell> unsatisfied = new ArrayList<>();
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

    public void swapRandomEmptyCell(Cell cell){
        Random random = new Random();
        int emptySize = emptyCells.size();
        int cnt = 0;
        for(Cell c: emptyCells){
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


    public void reset() {
        myCells.clear();
        initializeCells(similar,rbRatio,empty);
    }





}