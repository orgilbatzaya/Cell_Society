/*
@author Amy Kim
 */

package Cell;

import Grid.*;

/**
 * LifeCell class for Life of Game.
 */
public class LifeCell extends Cell {
    public static final int ALIVE = 0;
    public static final int DEAD = 1;

    public LifeCell(int alive, int x, int y) {
        super(alive, alive, x, y);
    }

    /**
     *
     * @param g LifeGrid
     * it checks current State so that set Next State.
     */
    @Override
    public void checkNeighbors(Grid g) {
        var cnt = 0;
        g.getCellsNear(this);
        for(var neighbor : g.getCellsNear(this)) {
            if(neighbor.getCurrentState() == ALIVE) cnt ++; //counting how many alive
        }

        if(this.getCurrentState() == ALIVE) { //set next state for alive cells
            if(cnt < 2) this.setNextState(DEAD); //if neighbor which alives less than 2, it will dead
            else if(cnt == 2 || cnt == 3) this.setNextState(ALIVE);
            else this.setNextState(DEAD); // Otherwise, dead
        } else {
            if(cnt == 3) this.setNextState(ALIVE);
        }
    }
}