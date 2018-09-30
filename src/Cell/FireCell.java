/*
@author Amy Kim
 */

package Cell;

import Grid.*;
import javafx.scene.Group;

import java.util.Random;

/**
 * This is FireCell extending Cell class.
 */
public class FireCell extends Cell {
    public static final int TREE = 0;
    public static final int FIRE = 1;
    public static final int GROUND = 2;


    private double prob;

    public FireCell(int stateOne, int x, int y) {
        super(stateOne, x, y);
    }

    @Override
    public int getMaxState(){
        return GROUND;
    }

}