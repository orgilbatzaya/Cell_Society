package Cell;

import Grid.Grid;

public class LifeCell extends Cell {
    public static final int ALIVE = 0;
    public static final int DEAD = 1;

    public LifeCell(int alive, int x, int y) {
        super(alive, alive, x, y);
    }

    @Override
    public void checkNeighbors(Grid g) {
        var cnt = getMyNeighbors().stream().filter(c -> c.getCurrentState() == ALIVE).count();
        if(getCurrentState() == ALIVE) {
            if(cnt < 2) setNextState(DEAD);
            else if(cnt == 2 || cnt == 3) setNextState(ALIVE);
            else setNextState(DEAD);
        } else {
            if(cnt == 3) setNextState(ALIVE);
        }
    }
}