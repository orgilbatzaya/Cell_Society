///**
// * @author yk154
// */
//
//package Cell;
//
//import Cell.Cell;
//import Grid.Grid;
//import Grid.WatorGrid;
//import java.util.ArrayList;
//import java.util.Random;
//
//
//
//public class FishCell extends Cell {
//    public static final int WATER = 0;
//    public static final int FISH = 1;
//    public static final int SHARK = 2;
//    public ArrayList<Cell> waterNeigh;
//
//    public int breedingTime;
//    private boolean edible;
//
//    public FishCell(int stateOne, int statetwo, int x, int y, int breedingTime) {
//        super(stateOne, statetwo, x, y);
//        this.breedingTime = breedingTime;
//    }
//
////    public void checkNeighbors(WatorGrid g){
////        var cnt = 0;
////        if(getCurrentState() == FISH){
////            getWaterNeighbors(new int[]{ });
////        }
////    }
////
////    public void getWaterNeighbors(){
////        waterNeigh.add();
////    }
//
//}
////public class FishCell extends Cell {
////    public static final int WATER = 0;
////    public static final int FISH = 1;
////    public static final int SHARK = 2;
////    public int breedingTime;
////    List<Cell> waterNeig;
////    private boolean edible;
////
////    public FishCell(int stateOne, int statetwo, int x, int y) {
////        super(stateOne, statetwo, x, y);
////    }
////
////    public void getNeighbors(WatorGrid g){
////        List<Cell> temp;
////        temp = g.getCellsNear(this);
////        for(Cell c:temp){
////            if(c.getCurrentState() != FISH || c.getCurrentState() != SHARK){
////                myNeighbors.add(c);
////            }
////        }
////    }
////
////    public List<Cell> getMyNeighbors(){
////        return myNeighbors;
////    }
////
////    public void checkNeighbors(Grid g){
////        for(var neighbor : getMyNeighbors()) {
////            if(neighbor.getCurrentState() == WATER) {
////                waterNeig.add(neighbor);
////            }
////        }
////
////    }
////
////    public void lowerBreedingTime(){
////        breedingTime -= 1;
////    }
////
////    public boolean checkBreeding(){
////        return breedingTime == 0;
////    }
////
////    public boolean isEdible() {
////        return edible;
////    }
//
//
