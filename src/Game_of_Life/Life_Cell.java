package Game_of_Life;

import Cell.Cell;

import java.util.List;

public class Life_Cell extends Cell {


    public Life_Cell(int dead, int alive, int x, int y){
        super(dead, alive, x, y);
    }

    @Override
    public void checkNeighbors() {
        for (int i = 0; i < myNeighbors.size(); i++) {
            myNeighbors.get(i);
        }
    }
}
