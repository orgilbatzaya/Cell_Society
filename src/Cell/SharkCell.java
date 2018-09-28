/**
 * @author Amy Kim
 */

package Cell;

import Grid.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;


/**
 * SharkCell Class for choosing random fish that shark can go and eat that fish.
 */
public class SharkCell extends Cell{
    public static final int SHARK = 2;
    public static final int FISH = 1;
    public static final int WATER = 0;
    private int energy;
    private int breedingTime;
    private int energySaved;
    private int breedingTimeSaved;
    private boolean feeding;
    private List<Cell> myFish;
    private Random random;
    private int[] nextPos;
    private int[] babyPos;
    List<Cell> openSpots;

    public SharkCell(int stateOne, int stateTwo, int x, int y, int breedingTime, int energy){
        super(stateOne, stateOne, x, y);
        this.breedingTime = breedingTime;
        this.breedingTimeSaved = breedingTime;
        this.energy = energy;
        this.energySaved = energy;
        myNeighbors = new ArrayList<Cell>();
        random = new Random();

    }

    public void move(){
        breedingTime--;
        energy--;
        checkAlive();

        openSpots = findOpenSpots();
        eatFish();
        if(!feeding && openSpots.size() > 0){
            int choice = random.nextInt(openSpots.size());
            Cell goTo = openSpots.get(choice);
            goTo.setTaken();
            openSpots.remove(choice);
            nextPos[0] = goTo.getX();
            nextPos[1] = goTo.getY();
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

    public void eatFish(){
        myFish = new ArrayList<Cell>();
        for(var neighbor : myNeighbors) {
            if(neighbor.getCurrentState() == FISH){
                myFish.add(neighbor);
            }
        }
        if(myFish.size() > 0){
            Cell eatenFish = (FishCell) myFish.get(random.nextInt(myFish.size()));
            nextPos[0] = eatenFish.getX();
            nextPos[1] = eatenFish.getY();
            feeding = true;
            energy++;
        }
    }

    public void breed(){
        int choice = random.nextInt(openSpots.size());
        if(breedingTime == 0){
            Cell baby = openSpots.get(choice);
            babyPos[0] = baby.getX();
            babyPos[1] = baby.getY();
        }
    }

    public void checkAlive(){
        if(energy <= 0){
            nextState = WATER;
        }
    }

    public void resetEnergyAndBreed(){
        breedingTime = breedingTimeSaved;
        energy = energySaved;
    }

    public void setTaken(){
        
    }
}