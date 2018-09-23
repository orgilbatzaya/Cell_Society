/*
package Cell;

import Grid.Grid;

import java.util.List;

*/
/**
 * @author ob29
 *//*

public class SharkCell extends FishCell {
    public static final int SHARK = 2;
    public static final int FISH = 1;
    public static final int WATER = 0;
    private int energy;

    public SharkCell(int stateOne, int stateTwo, int x, int y) {
        super(stateOne, stateTwo, x, y);
        energy = 5;
    }

    @Override
    public void getNeighbors(Grid g){
        List<Cell> temp;
        temp = g.getCellsNear(this);
        for(Cell c:temp){
            if(c.getCurrentState() != EMPTY){
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
    }







}
*/

