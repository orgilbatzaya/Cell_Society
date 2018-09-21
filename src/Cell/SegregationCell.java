package Cell;

import Cell.Cell;
import Grid.Grid;
import java.util.List;

public class SegregationCell extends Cell {
    private double currentSatisfied;
    private double mySatisfaction;

    public SegregationCell(int stateOne, int stateTwo, int x, int y, int satisfiedRate) {
        super(stateOne, stateTwo, x, y);
        this.mySatisfaction = satisfiedRate;

    }

    @Override
    public void getNeighbors(Grid g){
        List<Cell> temp;
        temp = g.getCellsNear(this);
        for(Cell c:temp){
            if(c.getCurrentState() != 0){
                myNeighbors.add(c);
            }
        }
    }

    public void checkNeighbors(Grid g) {
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
