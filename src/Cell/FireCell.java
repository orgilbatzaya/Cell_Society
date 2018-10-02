/*
@author Amy Kim
 */

package Cell;
import javafx.scene.paint.Color;

/**
 * This is FireCell extending Cell class. Spreading of Fire simulation
 */
public class FireCell extends Cell {
    public static final int TREE = 0;
    public static final int FIRE = 1;
    public static final int GROUND = 2;

    public FireCell(int stateOne, int x, int y) {
        super(stateOne, x, y);
    }

    /**
     *
     * @return the integer value of Ground state. Ground is the maximum value among three states
     */
    @Override
    public int getMaxState(){
        return GROUND;
    }

    /**
     *
     * @return tree is green, fire is red and ground(or burnt ground) is black.
     */
    @Override
    public Color getColor(){
        if(currentState == TREE){
            myColor = Color.GREEN;
        } else if (currentState == FIRE){
            myColor = Color.RED;
        } else {
            myColor = Color.BLACK;
        }
        return myColor;
    }

}