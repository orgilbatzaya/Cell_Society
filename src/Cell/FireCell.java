/*
@author Amy Kim
 */

package Cell;

import Grid.*;

import java.util.Random;

/**
 * This is FireCell extending Cell class.
 */
public class FireCell extends Cell {
    public static final int TREE = 0;
    public static final int FIRE = 1;
    public static final int GROUND = 2;


    private double prob;

    public FireCell(int stateOne, int x, int y, double prob) {
        super(stateOne, stateOne, x, y);
        this.prob = prob;
    }

    /**
     *
     * @param g FireGrid
     *
     * it checks current State so that set Next State.
     */
    @Override
    public void checkNeighbors(Grid g) {
        if(getCurrentState() == GROUND || getCurrentState() == FIRE) setNextState(GROUND);
        else {
            var cnt = 0; //counting how many neighbor Fire.
            for(var neighbor : g.getCellsNear(this)) {
                if(neighbor.getCurrentState() == FIRE) cnt ++;
            }

            if(cnt > 0) {
                var random = new Random();
                if(random.nextDouble() < prob) {
                    setNextState(FIRE); //if less that probability, next state will be fire
                    return;
                }
            } setNextState(TREE); //otherwise, it keeps tree state
        }
    }
}