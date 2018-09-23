package Cell;

import Grid.LifeGrid;
import java.util.List;

public abstract class Cell {
    protected int currentState;
    private int nextState;
    private int xPos;
    private int yPos;
    private int gridSize;

    protected List<Cell> myNeighbors;

    public Cell(int stateOne, int stateTwo, int x, int y){
        currentState = stateOne;
        nextState = stateOne;
        xPos = x;
        yPos = y;
    }
    public int getX(){
        return xPos;
    }

    public void setX(int val){
        xPos = val;
    }

    public int getY(){
        return yPos;
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
}