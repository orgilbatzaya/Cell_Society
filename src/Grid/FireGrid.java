package Grid;

import Cell.Cell;

import java.util.List;

public class FireGrid extends Grid {
    public FireGrid(int size, List<List<Cell>> cells){
        super(size, cells);
    }

    @Override
    public List<Cell> getCellsNear(Cell cell){
        return null;
    }

    @Override
    public void updateEveryCell(){

    }

    public int checkStats(){
        return 0;
    }


}
