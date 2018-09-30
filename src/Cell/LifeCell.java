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
        super(alive, x, y);
    }

    @Override
    public int getMaxState() {
        return DEAD;
    }
}