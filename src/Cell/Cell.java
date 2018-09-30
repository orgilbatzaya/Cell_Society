package Cell;

import Grid.*;
import java.util.List;

/**
 * @author ob29
 * @author Amy Kim
 * Superclass for Cell objects
 * Containing shared fields/methods among all Cells
 * getNeighbors() and checkNeighbors() require a Grid object
 */

public abstract class Cell {
    protected int currentState;
    protected int nextState;
    private int xPos;
    private int yPos;
    public static final int EMPTY = 0;
    protected List<Cell> myNeighbors;


    /**
     *
     * @param stateOne
     * @param stateTwo
     * @param x
     * @param y
     */
    public Cell(int stateOne, int stateTwo, int x, int y){
        currentState = stateOne;
        nextState = stateOne;
        xPos = x;
        yPos = y;
    }

    public int getX(){
        return xPos;
    }

    public int getY(){
        return yPos;
    }

    public int getCurrentState(){
        return currentState;
    }

    public int getNextState(){
        return nextState;
    }

    public void setCurrentState(int state){
        currentState = state;
    }

    public void setNextState(int state) {
        nextState = state;
    }


    /**
     * Currently only used by Segregation (SegGrid). Depends on Grid's getCellsNear(Cell) method.
     * Fills myNeighbors field with appropriate neighbor Cells.
     * @param g a Grid object
     */
    public void getNeighbors(Grid g){
        List<Cell> temp;
        temp = g.getCellsNear(this);
        for(Cell c:temp){
            if(c.getCurrentState() != EMPTY){
                myNeighbors.add(c);
            }
        }
    }

    /**
     * currently return 0
     * @return the max value of states.
     */
    public abstract int getMaxState();

}