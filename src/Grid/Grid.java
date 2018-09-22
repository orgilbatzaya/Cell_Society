package Grid;

import Cell.Cell;

import java.util.List;
import java.util.ArrayList;

public class Grid extends AbstractGrid {
    //int size;
    //protected List<List<Cell>> myCells;

    public Grid(int size, List<List<Cell>> cells){
        super(size, cells);
        //this.size = size;
        //myCells = cells;


    }



    public List<Cell> getEmptyCells(int emptyVal){
        List<Cell> requiredCells = new ArrayList<>();
        for(List<Cell> row: myCells){
            for(Cell c: row){
                if(c.getCurrentState() == emptyVal){
                    requiredCells.add(c);
                }
            }
        }
        return requiredCells;
    }

    public void updateEveryCell() {}
}
