package Cell;

import Cell.Cell;
import Grid.Grid;
import Grid.SegGrid;
import java.util.List;

/**
* @author ob29
*/

public class SegregationCell extends Cell {
    private double currentSatisfied;
    private double mySatisfaction;
    public static final int RED = 1;
    public static final int BLUE = 2;
    public static final int EMPTY = 0;



    public SegregationCell(int stateOne, int stateTwo, int x, int y, double satisfiedRate) {
        super(stateOne, stateTwo, x, y);
        this.mySatisfaction = satisfiedRate;

    }

    //@Override
    public void getNeighbors(SegGrid g){
        List<Cell> temp;
        temp = g.getCellsNear(this);
        for(Cell c:temp){
            if(c.getCurrentState() != EMPTY){
                myNeighbors.add(c);
            }
        }
    }


    public void checkNeighbors(SegGrid g) {
        int happyCells = 0;
        this.getNeighbors(g);
        for(int i = 0; i < myNeighbors.size(); i++) {
             if(currentState == myNeighbors.get(i).getCurrentState()){
                 happyCells ++;
             }
        }
        currentSatisfied = happyCells/myNeighbors.size();
    }


    public boolean isSatisfied(){
        return currentSatisfied >= mySatisfaction;
    }




}
