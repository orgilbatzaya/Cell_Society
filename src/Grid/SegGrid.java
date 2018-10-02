package Grid;

import Cell.Cell;
import Cell.SegregationCell;
import java.util.*;

/**
 * @author ob29
 * @author Amy Kim
 * Sub Grid class for Segregation simulation.
 */

public class SegGrid extends Grid {
    private final int similar;
    private final double rbRatio;
    private final double empty;

    public SegGrid(int size, int similar, double rbRatio, double empty ){
        super(size);
        myCells = new ArrayList<ArrayList<Cell>>();
        this.similar = similar;
        this.rbRatio = rbRatio;
        this.empty = empty;
        initializeCells();
    }

    /**
     * Fills myCells with new Segregation Cells, with random placement of
     * red/blue/empty cells depending on User input, along with a
     * similarity/satisfaction threshold for all non-empty cells
     *
     */
    public void initializeCells(){
        int numEmpty = (int) (empty*size*size);
        int numRed = (int) (rbRatio*(size*size - numEmpty));
        int numBlue = (size*size) - numEmpty - numRed;
        var states = randomizeStates(numEmpty,numRed,numBlue);

        for(int i = 0; i < size; i++){
            var row = new ArrayList<Cell>();
            for(int j = 0; j < size; j++){
                int state = (int) states.pop();
                var cell = new SegregationCell(state,i,j,similar);
                row.add(cell);
            }
            myCells.add(row);
        }
    }

    /**
     * Randomizes the orderings of red, blue, and empty cells
     * @param empty
     * @param red
     * @param blue
     * @return Stack
     */
    private Stack randomizeStates(int empty, int red, int blue){
        Stack<Integer> bagOfStates = new Stack<>();

        for(int i = 0; i < empty; i++){
            bagOfStates.add(SegregationCell.EMPTY);
        }
        for(int i = 0; i < red; i++){
            bagOfStates.add(SegregationCell.RED);
        }
        for(int i = 0; i < blue; i++){
            bagOfStates.add(SegregationCell.BLUE);
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
        Random random = new Random();
        List<Cell> target = getUnsatisfiedCells();
        // get colors
        ArrayList<Integer> values = new ArrayList<>();
        for(var cell: target) {
            values.add(cell.getCurrentState());
        }
        // shuffle colors
        Collections.shuffle(values);
        // assign shuffled colors in order
        for(int i = 0 ; i < target.size() ; i ++) {
            target.get(i).setNextState(values.get(i));
        }

        updateStates();
        checkStats();
        System.out.println(Arrays.toString(getStats()));
    }

    public List<Cell> getUnsatisfiedCells() {
        List<Cell> unsatisfied = new ArrayList<>();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                var cell = myCells.get(x).get(y);
                if (cell.getCurrentState() != SegregationCell.EMPTY ) {
                    checkNeighbors(cell);
                    if(!((SegregationCell) cell).isSatisfied()){
                        unsatisfied.add(cell);
                    }
                } else unsatisfied.add(cell);
            }
        }
        return unsatisfied;
    }


    /**
     * swaps the next states of current cell and random empty cell
     * removes formerly empty cell from emptyCells
     * @param cell
     */

    /**
     * calculates overall satisfaction of non-empty cells
     * @return double
     */
    public double checkStats() {
        int numSatisfied = 0;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if(((SegregationCell) myCells.get(x).get(y)).isSatisfied()){
                    numSatisfied++;
                }
            }
        }
        return (double)(numSatisfied)/(size*size);
    }

    /**
     * Overrides checkNeighbors() in Cell
     * Calculates currentSatisfied, which is the proportion of neighbor Cells
     * having the same currentState to all neighbors Cells
     * @param cell
     */
    @Override
    public void checkNeighbors(Cell cell) {
        var myNeighbors = getCellsNear(cell);
        ((SegregationCell)cell).updateSatisfaction(myNeighbors);
    }

    @Override
    public double[] getStats(){
        int numSatisfied = 0;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if(((SegregationCell) myCells.get(x).get(y)).isSatisfied()){
                    numSatisfied++;
                }
            }
        }
        return new double[]{(double)(numSatisfied)/(size*size*(1-empty))};
    }
}