package Cell;

import javafx.scene.paint.Color;
import java.util.ArrayList;

/**
 * @author ob29
 * @author Amy Kim
 * Sub Cell class for Segregation Simulation
 */

public class SegregationCell extends Cell {
    private double currentSatisfied; //variable
    private double mySatisfaction; //set for all SegregationCells in a certain simulation
    public static final int make_int = 100;// for example, by multiplying this value, 0.6 be 60
    public static final int RED = 1;
    public static final int BLUE = 2;
    public static final int EMPTY = 0;


    /**
     * Constructs a SegregationCell
     * @param stateOne current state and next state
     * @param x X position
     * @param y Y position
     * @param satisfiedRate specified at start of simulation, base threshold of tolerating
     *                      neighbor cells, must be at least satisfiedRate or will move itself
     */
    public SegregationCell(int stateOne, int x, int y, double satisfiedRate) {
        super(stateOne, x, y);
        this.mySatisfaction = satisfiedRate;
        myNeighbors = new ArrayList<Cell>();
    }


    /**
     * Must be at least mySatisfaction satisfied to not move itself
     * @return boolean. IsSatisfied or Not
     */
    public boolean isSatisfied(){
        return currentSatisfied * make_int >= mySatisfaction;
    }

    /**
     * counting the number of the cell which is same as currentSate and nonEmpty cells.
     * Then, calculate currentSatisfied.
     * @param neighbors this is ArrayList of cell; which are neighbors
     */
    public void updateSatisfaction(ArrayList<Cell> neighbors) {
        int cnt = 0;
        int nonEmpty = 0;
        for(var n: neighbors) {
            if(n.getCurrentState() == currentState) cnt ++;
            if(n.getCurrentState() != EMPTY) nonEmpty ++;
        }
        currentSatisfied = cnt/((double) nonEmpty);
    }

    /**
     *
     * @return Blue is holding the maximum value
     */
    @Override
    public int getMaxState() {
        return BLUE;
    }

    /**
     * Red is red, blue is blue and Empty cell is Gray.
     * @return
     */
    @Override
    public Color getColor(){
        if(currentState == RED){
            myColor = Color.RED;
        } else if (currentState == BLUE){
            myColor = Color.BLUE;
        } else {
            myColor = Color.GRAY;
        }
        return myColor;
    }
}