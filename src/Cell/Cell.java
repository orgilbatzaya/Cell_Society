package Cell;
import java.util.List;
import javafx.scene.paint.Color;


/**
 * @author ob29
 * @author Amy Kim
 * Superclass for Cell objects
 * Containing shared fields/methods among all Cells
 */

public abstract class Cell {
    protected int currentState;
    protected int nextState;
    private int xPos;
    private int yPos;
    protected Color myColor;
    protected List<Cell> myNeighbors;


    /**
     *
     * @param stateOne
     * @param x
     * @param y
     */
    public Cell(int stateOne, int x, int y){
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
     * abstract method
     * @return the max value of states for each simulation.
     */
    public abstract int getMaxState();


    /**
     * abstract method
     * @return Color for each states for simulation.
     */
    public abstract Color getColor();

}