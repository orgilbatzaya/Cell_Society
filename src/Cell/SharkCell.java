package Cell;

import Grid.Grid;
import Grid.WatorGrid;

import java.util.List;

/**
 * @author ob29
 */


public class SharkCell extends FishCell {
    public static final int SHARK = 2;
    public static final int FISH = 1;
    public static final int WATER = 0;
    private int energy;
    private int breedingTime;

    public SharkCell(int stateOne, int stateTwo, int x, int y) {
        super(stateOne, stateTwo, x, y);
        energy = 5;
    }

    //@Override
    public void getNeighbors(WatorGrid g){
        List<Cell> temp;
        temp = g.getCellsNear(this);
        for(Cell c:temp){
            if(c.getCurrentState() != SHARK){
                myNeighbors.add(c);
            }
        }
    }

    public List<Cell> getMyNeighbors(){
        return myNeighbors;
    }


    public void checkNeighbors(WatorGrid g) {
        int happyCells = 0;
        this.getNeighbors(g);
        for(int i = 0; i < myNeighbors.size(); i++) {
            if(currentState == myNeighbors.get(i).getCurrentState()){
                happyCells ++;
            }
        }
    }

    public void lowerBreedingTime(){
        breedingTime -= 1;
    }

    public boolean checkBreeding(){
        return breedingTime == 0;
    }

    public boolean checkFishNear(){
        for(Cell c: myNeighbors){
            if(c instanceof FishCell) {
                return true;
            }
        }
        return false;
    }








}

