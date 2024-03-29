
package Grid;

import Cell.Cell;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ob29
 * @author Amy Kim
 * Superclass for Grid objects
 * Containing shared fields/methods among all Grids.
 * Assumes existence of a collection of Cell objects
 */

public abstract class Grid {
    protected int size;
    protected ArrayList<ArrayList<Cell>> myCells;
    protected int type;

    public Grid(int size) {
        this.size = size;
        //this.type = type;
    }

    public abstract void initializeCells();
    /**
     * Checks if an (x,y) pair is bounded within a Grid of dimension size * size
     * @param x int
     * @param y int
     * @return boolean
     */
    public boolean inBounds(int x, int y) {
        if(x < 0 || x >= size){
            return false;
        }
        if(y < 0 || y >= size){
            return false;
        }
        return true;
    }


    /**
     * Given some Cell in the Grid, finds and stores all (8) adjacent positions
     * regardless of whether these positions are bounded
     * @param cell Cell object
     * @return List of int arrays
     */
    public List<int[]> getNearCellPositions(Cell cell) {
        List<int[]> positions = new ArrayList<>();
        int xPos = cell.getX();
        int yPos = cell.getY();

        if(inBounds(cell.getX(), cell.getY())){
            positions.add(new int[]{xPos -1, yPos});//sides
            positions.add(new int[]{xPos +1, yPos});
            positions.add(new int[]{xPos, yPos-1});
            positions.add(new int[]{xPos, yPos+1});

            positions.add(new int[]{xPos-1, yPos-1});//diagonals
            positions.add(new int[]{xPos-1, yPos+1});
            positions.add(new int[]{xPos+1, yPos-1});
            positions.add(new int[]{xPos+1, yPos+1});
        }
        return positions;
    }

    /**
     * Given some Cell in the Grid, finds and stores Adjacent Cell objects.
     * Depends on getNearCellPositions() and if these positions are bounded
     * @param cell Cell object
     * @return List of Cell objects
     */
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

    /**
     * applies toroidal wrapping
     * @param positions a list of int[2] x,y positions
     * @return a list of Cells with positions modified
     */
    public List<Cell> getCellsNearToroidal(List<int[]> positions){
        List<Cell> nearCells = new ArrayList<Cell>();
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
     * Executes a "step" in LifeGrid and FireGrid
     * Overridden by SegGrid
     * Depends on Cell's checkNeighbors() and setCurrentState() methods
     */
    public void updateEveryCell() {
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                Cell cell = myCells.get(x).get(y);
                checkNeighbors(cell);
            }
        }
        updateStates();
        System.out.println(Arrays.toString(getStats()));
    };


    /**
     * sets the current state to the determined next state
     */
    public void updateStates(){
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                Cell cell = myCells.get(x).get(y);
                cell.setCurrentState(cell.getNextState());
            }
        }
    }

    /**
     * Searches through Grid to find all Cells with certain state
     * @param state an int defining a specific cell state
     * @return List of Cells with required state
     */
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

    /**
     * Called within updateEveryCell, or each step, on each cell in the grid
     * to apply simulation-specific rules
     * @param cell
     */
    public abstract void checkNeighbors(Cell cell);

    /**
     * Calculates proportions of different Cell quantities in each simulation
     * For Segregation, determines only the overall satisfaction rate
     * @return a double[]
     */
    public abstract double[] getStats();


    public ArrayList<ArrayList<Cell>> getGrid() {
        return myCells;
    };




}