/**
 * @author Amy Kim
 */
package Cell;

import Grid.*;

import java.util.ArrayList;
import java.util.List;


/**
 * WaTor Cell extending cell.
 * This is for WaTor.
 */

public class WaTorCell extends Cell{
    FishCell fish = new FishCell();
    SharkCell shark = new SharkCell();

    public static final int SHARK = 2;
    public static final int FISH = 1;
    public static final int WATER = 0;

    private int energy;
    private int breedingTime;
    private int fishChronons = 0; //reproduce
    private int sharkChronons = 0; //reproduce
    private int[] fishHead;
    private int[] SharkHead;


    public WaTorCell(int stateOne, int x, int y, int breedingTime, int energy) {
        super(stateOne, stateOne, x, y);
        this.breedingTime = breedingTime;
        this.energy = energy;
    }

    @Override
    public void checkNeighbors(Grid g){
        if(getCurrentState() == FISH){ //set Next state for fish
            fishMove(g);
        }else if(getCurrentState() == SHARK){ //set Next state fo shark
            sharkMove(g);
            sharkDead(g); // checks energy of shark
        }else if(getCurrentState() == WATER){ //set Next state for water
            // This is chosen random position for fish that can moves. This is coming from fish.move
            this.setNextState((fishHead[0] == getX() && fishHead[1] == getY()) ? FISH : WATER); //fish moves to this block of water OR nothing comes, still water
        }
    }

    public void fishMove(Grid g){
        List<int[]> positions = new ArrayList<>();
        var cnt =0;
        for(var neighbor : g.getCellsNear(this)) {
            if(neighbor.getCurrentState() == WATER){
                positions.add(new int[]{neighbor.getX(), neighbor.getY()});
                cnt ++;
                System.out.println(cnt);
            }
        }
        System.out.println(cnt);
        if(cnt > 0) { //check if fish can move or not
            fishHead = fish.move(positions, cnt); //if can move, assign where to move in fishHead.
//            System.out.println(fishHead);
            if(fishBreed(g)) this.setNextState(FISH);
            else this.setNextState(WATER); //if movable, it becomes Water state.
        }else {
            if((SharkHead[0] == getX() && SharkHead[1] == getY())){
                this.setNextState(SHARK);
            }else{
                this.setNextState(WATER);
            }
        }
    }

    public void sharkMove(Grid g){
        List<int[]> positions = new ArrayList<>();
        var cnt =0;
        for(var neighbor : g.getCellsNear(this)) {
            if(neighbor.getCurrentState() == FISH){
                positions.add(new int[]{neighbor.getX(), neighbor.getY()});
                cnt ++;
            }
        }
        if(cnt > 0) { //check if shark can move or not
            SharkHead = shark.move(positions, cnt); //if can move, assign where to move in sharkHead.
            this.setNextState(WATER); //if movable, it becomes Water state.
            if(sharkBreed(g)) this.setNextState(SHARK);
            energy ++; //shark eats fish so energy up
        } else {
            this.setNextState(SHARK); //shark cannot move so just stay current state.
            energy --; //shark lost its energy cuz it doesn't eat fish
        }

    }

    public void sharkDead(Grid g){
        this.setNextState((shark.dead(energy, g)) ? WATER : SHARK);
    }


    /**
     *
     * @param g WaTor grid
     * @return boolean value if fish reproduces or not
     */
    public boolean fishBreed(Grid g){
        if(getCurrentState() == FISH){
            if(getNextState() == WATER || getNextState() == FISH) fishChronons ++;
            else fishChronons --;
        }
        if(fishChronons == breedingTime) {
            fishChronons = 0;
            return true;
        }
        return false;
    }

    /**
     *
     * @param g WaTor grid
     * @return boolean value if shark reproduces or not
     */
    public boolean sharkBreed(Grid g){
        if(getCurrentState() == SHARK){
            if(getNextState() == WATER || getNextState() == SHARK) sharkChronons ++;
        }else sharkChronons --;

        if(sharkChronons == breedingTime){
            sharkChronons =0;
            return true;
        }
        return false;
    }
}