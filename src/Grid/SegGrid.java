package Grid;

import Cell.Cell;
import Cell.SegregationCell;

import java.util.List;

import java.util.ArrayList;

/*
@author ob29
 */

public class SegGrid extends Grid {

    public SegGrid(int size, List<List<Cell>> cells){
        super(size, cells);
    }

    public void initializeCells(int similar, double rbRatio, double empty){
        for(int i = 0; i < size; i++){
            var row = new ArrayList<Cell>();
            for(int j = 0; j < size; j++){
                var cell = new SegregationCell(1,1,i,j,similar);
                String pos = checkCellPos(i,j,size);
                cell.setPosition(pos);
                row.add(cell);
            }
            myCells.add(row);
        }
    }

    public String checkCellPos(int x, int y, int size){
        if(x == 0 && y == 0 || x == size-1 && y == size-1 ||
                x == size-1 && y == 0 || x == 0 && y == size-1){
            return "corner";
        }
        else if(x == 0 || x == size-1 || y == 0 || y == size-1){
            return "edge";
        }
        else return "inner";
    }

    public List<Cell> getCellsNear(Cell cell) {
        List<Cell> nearCells = new ArrayList<Cell>();
        List<int[]> nearCellPs = getNearCellPositions(cell);
        for(int[] pos:nearCellPs){
            if(isValidPosition(pos[0],pos[1])){
                nearCells.add(myCells.get(pos[0]).get(pos[1]));
            }
        }
        return nearCells;
    }

        public List<int[]> getNearCellPositions(Cell cell){
            List<int[]> nearCellPositions = new ArrayList<>();
            int xPos = cell.getX();
            int yPos = cell.getY();
            nearCellPositions.add(new int[]{xPos - 1, yPos});
            nearCellPositions.add(new int[]{xPos + 1, yPos});
            nearCellPositions.add(new int[]{xPos, yPos - 1});
            nearCellPositions.add(new int[]{xPos, yPos + 1});

            nearCellPositions.add(new int[]{xPos - 1, yPos - 1});
            nearCellPositions.add(new int[]{xPos - 1, yPos + 1});
            nearCellPositions.add(new int[]{xPos + 1, yPos - 1});
            nearCellPositions.add(new int[]{xPos + 1, yPos + 1});
            return nearCellPositions;
        }


    public boolean isValidPosition(int x, int y){
        if(x < 0 || x >= size){
            return false;
        }
        if(y < 0 || y >= size){
            return false;
        }
        return true;
    }

    public List<Cell> getEmptyCellsNear(){

    }

    public boolean inBounds(Cell cell){

    }

    public  void updateEveryCell(){

    }

    public int checkStats(){

    }




}
