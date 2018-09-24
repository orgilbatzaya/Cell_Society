/**
 * @author Amy Kim
 */

package Cell;

import Cell.Cell;
import Grid.Grid;
import Grid.WatorGrid;
import java.util.ArrayList;
import java.util.Random;



public class FishCell{
    private ArrayList<Cell> moveOptions;

    public void setFishMove(ArrayList<Cell> moveOptions){
        this.moveOptions = moveOptions;
    }

    public ArrayList<Cell> getFishMove() {



        return moveOptions;
    }


    public ArrayList<Cell> findWater(){
        return moveOptions;
    }

    public void reproduce(){

    }


}

