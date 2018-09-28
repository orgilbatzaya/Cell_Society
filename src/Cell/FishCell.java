/**
 * @author Amy Kim
 */

package Cell;

import Cell.Cell;
import Grid.Grid;
import Grid.WatorGrid;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This is class to choose random water position so that fish can move.
 */

public class FishCell extends Cell{
    public static final int SHARK = 2;
    public static final int FISH = 1;
    public static final int WATER = 0;
    private int breedingTime;
    private Random random;
    List<Cell> openSpots;
    private int[] babyPos;




    public FishCell(int stateOne, int stateTwo, int x, int y, int breedingTime, int energy){
        super(stateOne, stateOne, x, y);
        this.breedingTime = breedingTime;
        myNeighbors = new ArrayList<Cell>();
        random = new Random();
    }


    public void fishMove(){
        breedingTime--;
        if(openSpots.size() > 0){
            int choice = random.nextInt(openSpots.size());
            Cell goTo = openSpots.get(choice);
            openSpots.remove(choice);
            nextState = WATER;
        }
        breed();

    }

    public ArrayList<Cell> findOpenSpots(){
        ArrayList<Cell> open = new ArrayList<Cell>();
        for(var neighbor: myNeighbors){
            if(neighbor.getCurrentState() == WATER){
                open.add(neighbor);
            }
        }
        return open;
    }



    /**
     *
     */
    public void breed(){
        int choice = random.nextInt(openSpots.size());
        if(breedingTime == 0){
            Cell baby = openSpots.get(choice);
            babyPos[0] = baby.getX();
            babyPos[1] = baby.getY();
        }
    }

}
