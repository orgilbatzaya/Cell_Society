/*
@author Amy Kim
 */

package Cell;

import javafx.scene.paint.Color;

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

    @Override
    public Color getColor(){
        if(currentState == ALIVE){
            myColor = Color.BLACK;
        } else {
            myColor = Color.GRAY;
        }
        return myColor;
    }
}