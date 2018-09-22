package Grid;

import Cell.Cell;

import java.util.List;
import java.util.ArrayList;

public class FireGrid extends Grid {
    public FireGrid(int size, List<List<Cell>> cells){
        super(size, cells);
    }

    @Override
    public List<Cell> getCellsNear(Cell cell){
        int xPos = cell.getX();
        int yPos = cell.getY();
        List<Cell> cellsNear = new ArrayList<Cell>();
        //adjacent
        cellsNear.add(myCells.get(xPos - 1).get(yPos));
        cellsNear.add(myCells.get(xPos + 1).get(yPos));
        cellsNear.add(myCells.get(xPos).get(yPos - 1));
        cellsNear.add(myCells.get(xPos).get(yPos + 1));
        //diagonals
        cellsNear.add(myCells.get(xPos - 1).get(yPos - 1));
        cellsNear.add(myCells.get(xPos - 1).get(yPos + 1));
        cellsNear.add(myCells.get(xPos + 1).get(yPos - 1));
        cellsNear.add(myCells.get(xPos + 1).get(yPos + 1));

        return cellsNear;

    }

    @Override
    public List<Cell> getEmptyCellsNear(){
        return null;
    }


    @Override
    public void updateEveryCell(){

    }

    @Override
    public int checkStats(){
        return 0;
    }


}
