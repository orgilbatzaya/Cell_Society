package Grid;

import Cell.Cell;

import java.util.ArrayList;
import java.util.List;

public class LifeGrid extends Grid {
    public LifeGrid(int size, List<List<Cell>> cells){
        super(size, cells);
    }


    @Override
    public void updateEveryCell(){


    }

    @Override
    public double checkStats(){
        return 0;
    }

}
