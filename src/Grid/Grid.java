
package Grid;

import Cell.Cell;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ob29
 * Superclass for Grid objects
 * Containing shared fields/methods among all Grids.
 * Assumes existence of a collection of Cell objects
 */

public abstract class Grid {
    protected int size;
    protected ArrayList<ArrayList<Cell>> myCells;

    //dummy constructor
    public Grid(int size) {
        this.size = size;
    }

    /**
     * Finds and stores all "empty" cells in a Grid. Misnomer.
     * @param emptyVal int value, usually between 0-2 inclusive,
     *                 doesn't actually have to mean "empty" but
     *                 any state.
     * @return a List of Cell objects
     */
    public List<Cell> getEmptyCells(int emptyVal){ //get all the empty cells
        List<Cell> requiredCells = new ArrayList<>();
        for(List<Cell> row: myCells){
            for(Cell c: row){
                if(c.getCurrentState() == emptyVal){
                    requiredCells.add(c);
                }
            }
        }
        return requiredCells;
    }

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
     * Executes a "step" in LifeGrid and FireGrid
     * Overridden by SegGrid
     * Depends on Cell's checkNeighbors() and setCurrentState() methods
     */
    public void updateEveryCell() {
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                Cell cell = myCells.get(x).get(y);
                checkNeighbors(cell);
                cell.setCurrentState(cell.getNextState());
            }
        }
    };

    public abstract void checkNeighbors(Cell cell);

    public ArrayList<ArrayList<Cell>> getGrid() {
        return myCells;
    };
}