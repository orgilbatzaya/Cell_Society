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
    private int breedingTimeSaved;
    private Random random;
    List<Cell> openSpots;
    private int[] nextPos;
    private int[] babyPos;
    private boolean taken = false;
    private boolean moving = false;
    private boolean birthing = false;




    public FishCell(int stateOne, int stateTwo, int x, int y, int breedingTime){
        super(stateOne, stateOne, x, y);
        this.breedingTime = breedingTime;
        this.breedingTimeSaved = breedingTime;
        myNeighbors = new ArrayList<Cell>();
        nextPos = new int[]{x,y};
        babyPos = new int[]{x,y};
        random = new Random();
    }


    public void move(){
        breedingTime--;
        openSpots = findOpenSpots();

        //System.out.println(myNeighbors.size());
        if(openSpots.size() > 0 && !taken){
            moving = true;
            int choice = random.nextInt(openSpots.size());
            Cell goTo = openSpots.get(choice);
            goTo.setTaken();
            openSpots.remove(choice);
            nextPos[0] = goTo.getX();
            nextPos[1] = goTo.getY();
            nextState = WATER;
        }
        breed();
        openSpots.clear();

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

    public void breed(){
        if (breedingTime <= 0 && openSpots.size() > 0) {
            birthing = true;
            int choice = random.nextInt(openSpots.size());
            Cell baby = openSpots.get(choice);
            babyPos[0] = baby.getX();
            babyPos[1] = baby.getY();
        }
    }

    public void resetEnergyAndBreed(){
        breedingTime = breedingTimeSaved;
    }

    public void setTaken(){
        taken = true;
    }

    public void unTaken(){
        taken = false;
    }

    public boolean isMoving(){
        return moving;
    }

    public int[] getNextPos(){
        return nextPos;
    }

    public int[] getBabyPos(){
        return babyPos;
    }
    @Override
    public void getNeighbors(Grid g){
        List<Cell> temp;
        temp = g.getCellsNear(this);
        for(Cell c:temp){
            myNeighbors.add(c);

        }
    }

    public void resetNextState(){
        nextState = currentState;
    }

    public boolean checkTaken(){
        return taken;
    }


    public int getBreedingTime(){
        return breedingTime;
    }

    public boolean isBirthing(){
        return birthing;
    }

    public void unBirthing(){
        birthing = false;
    }


}
