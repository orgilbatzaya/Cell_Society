package Cell;

import Grid.Grid;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Amy Kim
 */
public class WaTorCell extends Cell {
    public static final int WATER = 1 << 0;
    public static final int FISH = 1 << 1;
    public static final int SHARK = 1 << 2;
    public static final int MOVE = 1 << 3;
    public static final int DIE = 1 << 4;
    public static final int BREED = 1 << 5;

    private int breedingTime, nextBreedingTime;
    private int energy, nextEnergy;

    private Cell nextLocation;

    public WaTorCell(int stateOne, int x, int y, int breedingTime, int energy) {
        super(stateOne, stateOne, x, y);
        this.breedingTime = breedingTime;
        this.energy = energy;
    }

    @Override
    public void checkNeighbors(Grid g) {
        nextLocation = null;

        if((currentState & SHARK) > 0) {
            // STARVE
            energy --;
            if(energy <= 0) setNextState(WATER + DIE);

            var fishes = pickFishCells(g);
            var waters = pickWaterCells(g);

            if(fishes.size() > 0) {
                var randomFishCell = fishes.get(new Random().nextInt(fishes.size()));
                setNextState(WATER + MOVE);
                nextLocation = randomFishCell;
            } else if(waters.size() > 0){
                var randomWaterCell = waters.get(new Random().nextInt(waters.size()));
                setNextState(WATER + MOVE);
                nextLocation = randomWaterCell;
            } else {
                nextLocation = this;
            }
        } else if((currentState & FISH) > 0) {
            var waters = pickWaterCells(g);
            if(waters.size() > 0){
                var randomWaterCell = waters.get(new Random().nextInt(waters.size()));
                setNextState(WATER + MOVE);
                nextLocation = randomWaterCell;
            } else {
                nextLocation = this;
            }
        } else nextLocation = this;
    }

    private List<Cell> pickFishCells(Grid g) {
        var neighbors = g.getCellsNear(this);
        neighbors.removeIf(p -> p.getCurrentState() != FISH);
        return neighbors;
    }

    private List<Cell> pickWaterCells(Grid g) {
        var neighbors = g.getCellsNear(this);
        neighbors.removeIf(p -> p.getCurrentState() != WATER);
        return neighbors;
    }

    public Cell getNextLocation() { return nextLocation; }
    public int getEnergy() { return energy; }
    public void setEnergy(int energy) { this.energy = energy; }
    public int getBreedingTime() {return breedingTime;}
    public void setBreedingTime(int breedingTime) { this.breedingTime = breedingTime;}

    public int getNextBreedingTime() {
        return nextBreedingTime;
    }

    public void setNextBreedingTime(int nextBreedingTime) {
        this.nextBreedingTime = nextBreedingTime;
    }

    public int getNextEnergy() {
        return nextEnergy;
    }

    public void setNextEnergy(int nextEnergy) {
        this.nextEnergy = nextEnergy;
    }
}