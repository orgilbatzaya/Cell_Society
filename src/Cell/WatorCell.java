package Cell;

import Grid.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

public class WatorCell extends Cell {
    public static final int SHARK = 2;
    public static final int FISH = 1;
    public static final int WATER = 0;
    private int energy;
    private int energySaved;
    private int breedingTime;
    private int breedingTimeSaved;
    private List<Cell> myFish;
    private Random random;
    private int[] nextPos;
    private int[] babyPos;
    List<Cell> openSpots;
    private boolean feeding;
    private boolean taken = false;
    private boolean moving = false;
    private boolean birthing = false;

    public WatorCell(int stateOne, int x, int y, int breedingTime, int energy){
        super(stateOne, x, y);
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
        if(currentState == SHARK){
            sharkUpdate();
        }
        openSpots = findOpenSpots();
        if(!feeding && openSpots.size() > 0 && energy > 0){
            chooseNextPos(openSpots);
        }
        breed();
        feeding = false;
        openSpots.clear();
    }

    private ArrayList<Cell> findOpenSpots(){
        ArrayList<Cell> open = new ArrayList<Cell>();
        for(var neighbor: myNeighbors){
            if(neighbor.getCurrentState() == WATER){
                open.add(neighbor);
            }
        }
        return open;
    }

    private void sharkUpdate(){
        energy--;
        checkAlive();
        eatFish();
    }

    private void chooseNextPos(List<Cell> spots){
        int choice = random.nextInt(spots.size());
        Cell goTo = spots.get(choice);
        setTaken();
        moving = true;
        nextPos[0] = goTo.getX();
        nextPos[1] = goTo.getY();
        nextState = WATER;
        spots.remove(choice);
    }


    private void eatFish(){
        myFish = new ArrayList<Cell>();
        for(var neighbor : myNeighbors) {
            if(neighbor.getCurrentState() == FISH && !checkTaken()){
                myFish.add(neighbor);
            }
        }
        if(myFish.size() > 0 && energy > 0){
            chooseNextPos(myFish);
            feeding = true;
            energy++;
        }
    }

    private void breed(){
        if (breedingTime <= 0 && openSpots.size() > 0) {
            birthing = true;
            int choice = random.nextInt(openSpots.size());
            Cell baby = openSpots.get(choice);
            babyPos[0] = baby.getX();
            babyPos[1] = baby.getY();
        }
    }

    public void getNeighbors(Grid g){
        List<Cell> temp;
        temp = g.getCellsNear(this);
        for(Cell c:temp){
            myNeighbors.add(c);
        }
    }

    @Override
    public int getMaxState() {return SHARK;}

    private void checkAlive(){
        if(energy <= 0){
            nextState = WATER;
        }
    }

    public void resetEnergyAndBreed(){
        breedingTime = breedingTimeSaved;
        energy = energySaved;
    }

    public int getBreedingTime(){
        return breedingTime;
    }

    public void setTaken(){
        taken = true;
    }

    public void unTaken(){
        taken = false;
    }

    public boolean checkTaken(){
        return taken;
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

    public int getEnergy(){
        return energy;
    }

    public void clearNeighbors(){
        myNeighbors.clear();
    }

    @Override
    public Color getColor(){
        if(currentState == SHARK){
            myColor = Color.RED;
        } else if (currentState == FISH){
            myColor = Color.GREEN;
        } else {
            myColor = Color.BLUE;
        }
        return myColor;
    }


}