package Cell;

import Cell.Cell;
import Grid.Grid;
import Grid.SegGrid;

import java.util.ArrayList;
import java.util.List;

/**
* @author ob29
*/

public class SegregationCell extends Cell {
    private double currentSatisfied;
    public double mySatisfaction;
    private boolean taken = false;
    public static final int RED = 1;
    public static final int BLUE = 2;
    public static final int EMPTY = 0;



    public SegregationCell(int stateOne, int stateTwo, int x, int y, double satisfiedRate) {
        super(stateOne, stateTwo, x, y);
        this.mySatisfaction = satisfiedRate;
        myNeighbors = new ArrayList<Cell>();

    }

    public void getNeighbors(SegGrid g){
        List<Cell> temp;
        temp = g.getCellsNear(this);
        for(Cell c:temp){
            if(c.getCurrentState() != EMPTY){
                myNeighbors.add(c);
            }
        }
    }
    public List<Cell> getMyNeighbors(){
        return myNeighbors;
    }

    public void clearNeighbors(){
        myNeighbors.clear();
    }


    public void checkNeighbors(SegGrid g) {
        int happyCells = 0;
        for(int i = 0; i < myNeighbors.size(); i++) {
             if(currentState == myNeighbors.get(i).getCurrentState()){
                 happyCells ++;
             }
        }
        if(myNeighbors.size() > 0) {
            currentSatisfied = 100.0*happyCells / myNeighbors.size();
        }
        if(myNeighbors.size() == 0){
            currentSatisfied = 100;
        }
    }


    public boolean isSatisfied(){
        return currentSatisfied >= mySatisfaction;
    }

    public void setTaken(){
        taken = true;
    }

    public void unTaken(){
        taken = false;
    }

    public boolean checkTaken(){
        return taken;
    }

    public double getMySatisfaction(){
        return currentSatisfied;
    }




}
