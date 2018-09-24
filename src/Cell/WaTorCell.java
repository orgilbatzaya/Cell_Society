package Cell;

import Grid.WatorGrid;

import java.util.ArrayList;
import java.util.List;

public class WaTorCell extends Cell{
    FishCell fish = new FishCell();
    SharkCell shark = new SharkCell();

    public static final int SHARK = 2;
    public static final int FISH = 1;
    public static final int WATER = 0;

    private int energy;
    private int breedingTime;
    private int[] fishHead;
    private int[] SharkHead;
    private int turns = 0;

    public WaTorCell(int stateOne, int x, int y, int breedingTime, int energy) {
        super(stateOne, stateOne, x, y);
        this.breedingTime = breedingTime;
        this.energy = energy;
    }

    public void checkNeighbors(WatorGrid g){
        turns ++;
        if(getCurrentState() == FISH){
            fishMove(g);
        }else if(getCurrentState() == SHARK){
            sharkMove(g);
            sharkDead(g); // checks energy of shark
        }else if(getCurrentState() == WATER){
            this.setNextState((fishHead[0] == getX() && fishHead[1] == getY()) ? FISH : WATER); //fish moves to this block of water OR nothing comes, still water
        }
    }

    public void fishMove(WatorGrid g){
        List<int[]> positions = new ArrayList<>();
        var cnt =0;
        for(var neighbor : g.getCellsNear(this)) {
            if(neighbor.getCurrentState() == WATER){
                positions.add(new int[]{neighbor.getX(), neighbor.getY()});
                cnt ++;
            }
        }
        if(cnt > 0) { //check if fish can move or not
            fishHead = fish.move(positions, cnt); //if can move, assign where to move in fishHead.
            this.setNextState(WATER); //if movable, it becomes Water state.
        }else this.setNextState((SharkHead[0] == getX() && SharkHead[1] == getY()) ? SHARK : WATER);//Fish is eaten by shark or not
    }

    public void sharkMove(WatorGrid g){
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
            energy ++; //shark eats fish so energy up
        } else {
            this.setNextState(SHARK); //shark cannot move so just stay current state.
            energy --; //shark lost its energy cuz it doesnt eat fish
        }

    }

    public void sharkDead(WatorGrid g){
        this.setNextState((shark.dead(energy, g)) ? WATER : SHARK);
    }
}
