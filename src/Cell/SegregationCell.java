//package Cell;
//
//import Grid.Grid;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author ob29
// * Sub Cell class for Segregation Simulation
// */
//
//public class SegregationCell extends Cell {
//    private double currentSatisfied; //variable
//    private double mySatisfaction; //set for all SegregationCells in a certain simulation
//    private boolean taken = false;
//    public static final int RED = 1;
//    public static final int BLUE = 2;
//    public static final int EMPTY = 0;
//
//
//    /**
//     * Constructs a SegregationCell
//     * @param stateOne
//     * @param stateTwo
//     * @param x
//     * @param y
//     * @param satisfiedRate specified at start of simulation, base threshold of tolerating
//     *                      neighbor cells, must be at least satisfiedRate or will move itself
//     */
//    public SegregationCell(int stateOne, int stateTwo, int x, int y, double satisfiedRate) {
//        super(stateOne, stateTwo, x, y);
//        this.mySatisfaction = satisfiedRate;
//        myNeighbors = new ArrayList<Cell>();
//    }
//
//    public List<Cell> getMyNeighbors(){
//        return myNeighbors;
//    }
//
//    /**
//     * Overrides checkNeighbors() in Cell
//     * Calculates currentSatisfied, which is the proportion of neighbor Cells
//     * having the same currentState to all neighbors Cells
//     * @param g
//     */
//    @Override
//    public void checkNeighbors(Grid g) {
//        int happyCells = 0;
//        for(int i = 0; i < myNeighbors.size(); i++) {
//            if(currentState == myNeighbors.get(i).getCurrentState()){
//                happyCells ++;
//            }
//        }
//        if(myNeighbors.size() > 0) {
//            currentSatisfied = 100.0*happyCells / myNeighbors.size();
//        }
//        if(myNeighbors.size() == 0){
//            currentSatisfied = 100;
//        }
//    }
//
//    /**
//     * Must be at least mySatisfaction satisfied to not move itself
//     * @return
//     */
//    @Override
//    public boolean isSatisfied(){
//        return currentSatisfied >= mySatisfaction;
//    }
//
//    @Override
//    public void setTaken(){
//        taken = true;
//    }
//    @Override
//    public void unTaken(){
//        taken = false;
//    }
//    @Override
//    public boolean checkTaken(){
//        return taken;
//    }
//
//    public double getMySatisfaction(){
//        return currentSatisfied;
//    }
//
//
//
//
//}
