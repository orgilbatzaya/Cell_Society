package Cell;

import Grid.*;
import java.util.List;


public abstract class Cell {
    private int currentState;
    private int nextState;

    public Cell(int current, int next){
        this.currentState = current;
        this.nextState = next;
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