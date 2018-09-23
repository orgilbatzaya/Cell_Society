/*
@author yk154
 */

package Cell;

import Grid.FireGrid;
import Grid.LifeGrid;

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

    public void checkNeighbors(FireGrid g) {
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