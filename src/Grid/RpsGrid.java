package Grid;

import Cell.Cell;
import Cell.RpsCell;

import java.util.ArrayList;
import java.util.Random;

public class RpsGrid extends Grid {
    public static final int WHITE = 0;
    public static final int RED = 1;
    public static final int BLUE = 2;
    public static final int GREEN = 3;

    public RpsGrid(int size) {
        super(size);
        myCells = new ArrayList<ArrayList<Cell>>();
        initializeCells();
    }

    private void initializeCells(){
        for(int i = 0; i < size; i++){
            var row = new ArrayList<Cell>();
            for(int j = 0; j < size; j++){
                var cell = new RpsCell(WHITE, i, j);
                row.add(cell);
            }
            myCells.add(row);
        }
    }


    public void checkNeighbors(Cell cell){
        ((RpsCell) cell).checkCells(this);
    }
}
