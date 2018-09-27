package Box;

import Cell.Cell;
import Grid.Grid;

import java.util.List;

public abstract class Box{
    private Cell myCell;


    private int xPos;
    private int yPos;
    public static final int EMPTY = 0;
    protected List<Cell> myNeighbors;
    private Cell myCell;

    public Box(int x, int y){
        this.xPos = x;
        this.yPos = y;

    }

    public int getX(){
        return xPos;
    }



    public int getY(){
        return yPos;
    }


    public abstract void checkNeighbors(Grid g);

    /**
     * Currently only used by Segregation (SegGrid). Depends on Grid's getCellsNear(Cell) method.
     * Fills myNeighbors field with appropriate neighbor Cells.
     * @param g a Grid object
     */
    public void getNeighbors(Grid g){
        List<Cell> temp;
        temp = g.getCellsNear(myCell);
        for(Cell c:temp){
            if(c.getCurrentState() != EMPTY){
                myNeighbors.add(c);
            }
        }
    }
    /**
     * Currently only implemented in Segregation (SegGrid).
     */
    public boolean isSatisfied(){
        return false;
    }

    /**
     * Currently only used by Segregation (SegGrid).
     */
    public void clearNeighbors(){
        myNeighbors.clear();
    }

    /**
     * Currently only implemented in Segregation (SegGrid).
     */
    public void unTaken(){

    }

    /**
     * Currently only implemented in Segregation (SegGrid).
     */
    public void setTaken(){

    }

    /**
     * Currently only implemented in Segregation (SegGrid).
     */
    public boolean checkTaken(){
        return false;
    }

    public Cell getMyCell() {
        return myCell;
    }
}