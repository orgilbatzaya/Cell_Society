/**
 * @author Amy Kim
 */

package Cell;

import Grid.*;

import java.util.Arrays;
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
    private List<Cell> myFish;
    private Random random;
    private int[] nextPos;
    private int[] babyPos;
    List<Cell> openSpots;
    private boolean taken = false;
    private boolean feeding;
    private boolean moving = false;
    private boolean birthing = false;

    public SharkCell(int stateOne, int stateTwo, int x, int y, int breedingTime, int energy){
        super(stateOne, stateOne, x, y);
        this.breedingTime = breedingTime;
        this.breedingTimeSaved = breedingTime;
        this.energy = energy;
        this.energySaved = energy;
        myNeighbors = new ArrayList<Cell>();
        nextPos = new int[]{x,y};
        babyPos = new int[]{x,y};
        random = new Random();

    }

    public void move(){
        breedingTime--;
        energy--;
        checkAlive();

        openSpots = findOpenSpots();
        eatFish();
        if(!feeding && openSpots.size() > 0 && energy > 0){
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
        feeding = false;
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

    public void eatFish(){
        myFish = new ArrayList<Cell>();
        for(var neighbor : myNeighbors) {
            if(neighbor.getCurrentState() == FISH && !neighbor.checkTaken()){
                myFish.add(neighbor);
            }
        }
        if(myFish.size() > 0 && energy > 0){
            Cell eatenFish = (FishCell) myFish.get(random.nextInt(myFish.size()));
            eatenFish.setTaken();
            moving = true;
            nextPos[0] = eatenFish.getX();
            nextPos[1] = eatenFish.getY();
            feeding = true;
            energy++;
            nextState = WATER;
        }
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
        taken = true;
    }

    public void unTaken(){
        taken = false;
    }

    public int[] getNextPos(){
        return nextPos;
    }

    public int[] getBabyPos(){
        return babyPos;
    }

    public boolean isMoving(){
        return moving;
    }

    public void unMoving(){
        moving = false;
    }

    public boolean isBirthing(){
        return birthing;
    }

    public void unBirthing(){
        birthing = false;
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

    public int getEnergy(){
        return energy;
    }

    public int getBreedingTime(){
        return breedingTime;
    }



}