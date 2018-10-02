/*
@author Amy Kim
 */
package Cell;

import javafx.scene.paint.Color;

/**
 * LifeCell class for Life of Game
 */
public class LifeCell extends Cell {
    public static final int ALIVE = 0;
    public static final int DEAD = 1;

    public LifeCell(int alive, int x, int y) {
        super(alive, x, y);
    }

    /**
     *
     * @return int value of Dead state. Dead state is holding the maximum value
     */
    @Override
    public int getMaxState() {
        return DEAD;
    }

    /**
     *
     * @return Alive is pink and Dead cell is DimGray
     */
    @Override
    public Color getColor(){
        if(currentState == ALIVE){
            myColor = Color.PINK;
        } else {
            myColor = Color.DIMGRAY;
        }
        return myColor;
    }
}