/*
@author Amy Kim
 */

package Cell;

import Grid.*;

import java.util.Random;

public class FireCell extends Cell {
    public static final int TREE = 0;
    public static final int FIRE = 1;
    public static final int GROUND = 2;


    private double prob;

    public FireCell(int stateOne, int x, int y, double prob) {
        super(stateOne, stateOne, x, y);
        this.prob = prob;
    }

    @Override
    public void checkNeighbors(Grid g) {
        if(getCurrentState() == GROUND || getCurrentState() == FIRE) setNextState(GROUND);
        else {
            var cnt = 0;
            for(var neighbor : g.getCellsNear(this)) {
                if(neighbor.getCurrentState() == FIRE) cnt ++;
            }

            if(cnt > 0) {
                var random = new Random();
                if(random.nextDouble() < prob) {
                    setNextState(FIRE);
                    return;
                }
            } setNextState(TREE);
        }
    }
}