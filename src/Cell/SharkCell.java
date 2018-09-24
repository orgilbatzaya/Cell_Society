/**
 * @author Amy Kim
 */

package Cell;

import Cell.Cell;
import Grid.Grid;
import Grid.WatorGrid;
import java.util.ArrayList;
import java.util.Random;



public class SharkCell{
    private ArrayList<Cell> moveOptions;

    public void setSharkMove(ArrayList<Cell> moveOptions){
        this.moveOptions = moveOptions;
    }

    public ArrayList<Cell> getSharkMove() {
        return moveOptions;
    }


    public ArrayList<Cell> findFish(){
        return moveOptions;
    }

    public void reproduce(){

    }

    public void energy(){

    }


}