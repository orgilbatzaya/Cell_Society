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

    private int breedingTime;
    private int[] fishHead;
    private int[] SharkHead;

    public WaTorCell(int stateOne, int x, int y, int breedingTime) {
        super(stateOne, stateOne, x, y);
        this.breedingTime = breedingTime;
    }

    public void checkNeighbors(WatorGrid g){
        if(getCurrentState() == FISH){
            fishMove(g);
        }else if(getCurrentState() == SHARK){
            sharkMove(g);
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
        if(cnt > 0) { //check if fish can move or not
            SharkHead = shark.move(positions, cnt); //if can move, assign where to move in fishHead.
            this.setNextState(WATER); //if movable, it becomes Water state.
        } else this.setNextState(SHARK); //Fish cannot move so just stay current state.

    }
}
