package Cell;

import Grid.WatorGrid;

import java.util.ArrayList;

public class WaTorCell extends Cell{
    public static final int SHARK = 2;
    public static final int FISH = 1;
    public static final int WATER = 0;

    FishCell fish = new FishCell();
    SharkCell shark = new SharkCell();

    public int breedingTime;

    public WaTorCell(int stateOne, int x, int y, int breedingTime) {
        super(stateOne, stateOne, x, y);
        this.breedingTime = breedingTime;
    }

    public void checkNeighbors(WatorGrid g){
        var cnt = 0;
        if(getCurrentState() == FISH){

            fish.getFishMove();
        }else if(getCurrentState() == SHARK){

        }else if(getCurrentState() == WATER){

        }
    }


}
