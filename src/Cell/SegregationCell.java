package Cell;

import Grid.Grid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ob29
 * @author Amy Kim
 * Sub Cell class for Segregation Simulation
 */

public class SegregationCell extends Cell {
    private double currentSatisfied; //variable
    private double mySatisfaction; //set for all SegregationCells in a certain simulation
    private boolean taken = false;
    public static final int RED = 1;
    public static final int BLUE = 2;
    public static final int EMPTY = 0;


    /**
     * Constructs a SegregationCell
     * @param stateOne
     * @param stateTwo
     * @param x
     * @param y
     * @param satisfiedRate specified at start of simulation, base threshold of tolerating
     *                      neighbor cells, must be at least satisfiedRate or will move itself
     */
    public SegregationCell(int stateOne, int stateTwo, int x, int y, double satisfiedRate) {
        super(stateOne, stateTwo, x, y);
        this.mySatisfaction = satisfiedRate;
        myNeighbors = new ArrayList<Cell>();
    }


    /**
     * Must be at least mySatisfaction satisfied to not move itself
     * @return
     */
    public boolean isSatisfied(){
        return currentSatisfied*100 >= mySatisfaction;
    }

    public void updateSatisfaction(List<Cell> neighbors) {
        int cnt = 0;
        int nonEmpty = 0;
        for(var n: neighbors) {
            if(n.getCurrentState() == currentState) cnt ++;
            if(n.getCurrentState() != EMPTY) nonEmpty ++;
        }
        currentSatisfied = cnt/((double) nonEmpty);
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

    public int getMaxState() {
        return BLUE;
    }
}