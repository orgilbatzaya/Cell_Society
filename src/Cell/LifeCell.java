/*
@author yk154
 */

package Cell;

import Grid.LifeGrid;

public class LifeCell extends Cell {
    public static final int ALIVE = 0;
    public static final int DEAD = 1;

    public LifeCell(int alive, int x, int y) {
        super(alive, alive, x, y);
    }

    @Override
    public void checkNeighbors(LifeGrid g) {
        var cnt = 0;
        this.getNeighbors(g);
        for(var neighbor : this.getMyNeighbors()) {
            if(neighbor.getCurrentState() == ALIVE) cnt ++;
        }

        if(this.getCurrentState() == ALIVE) {
            if(cnt < 2) this.setNextState(DEAD);
            else if(cnt == 2 || cnt == 3) this.setNextState(ALIVE);
            else this.setNextState(DEAD);
        } else {
            if(cnt == 3) this.setNextState(ALIVE);
        }
    }
}