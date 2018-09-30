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
     * for WaTor
     */
    private boolean moving;
    private boolean birthing;


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

    public void setX(int val){
        xPos = val;
    }

    public void setY(int val){
        yPos = val;
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
    public int getMaxState() {return 0;}


    /**
     * for waTor
     */
    public void unTaken(){

    }

    public boolean checkTaken(){
        return false;
    }

    public boolean isMoving(){
        return moving;
    }

    public void unMoving(){
        moving = false;
    }

    public boolean isBirthing(){
        return birthing;
    }

    public void unBirthing(){
        birthing = false;
    }
    public void setTaken(){

    }


    public List<Cell> getMyNeighbors(){
        return myNeighbors;
    }


    public void clearNeighbors(){
        myNeighbors.clear();
    }


}