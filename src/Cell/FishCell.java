//package Cell;
//
//import Cell.Cell;
//import Grid.Grid;
//import Grid.WatorGrid;
//import java.util.List;
//
///**
// * @author ob29
// */
//
//public class FishCell extends Cell {
//    private double breedingTime;
//    private int turnsUntilBreed;
//
//    public static final int ALIVE = 1;
//    public static final int WATER = 0;
//
//
//    public FishCell(int stateOne, int stateTwo, int x, int y, int breedingTime) {
//        super(stateOne, stateTwo, x, y);
//        this.breedingTime = breedingTime;
//
//    }
//
//    public void getNeighbors(WatorGrid g){
//        List<Cell> temp;
//        temp = g.getCellsNear(this);
//        for(Cell c:temp){
//            myNeighbors.add(c);
//        }
//    }
//
//
//
//    public void checkNeighbors(WatorGrid g){
//        for(var lst: g.getCellsNear(this)){
//            continue;
//        }
//    }
//
//
//    @Override
//    public void checkNeighbors(Grid g) {
//
//    }
//}
//
