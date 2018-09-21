package Grid;

import Cell.Cell;

import java.util.List;

public class Grid extends AbstractGrid {
    int size;
    protected List<List<Cell>> myCells;

    public Grid(int size, List<List<Cell>> cells){
        super(size, cells);
        this.size = size;
        myCells = cells;
    }
}
