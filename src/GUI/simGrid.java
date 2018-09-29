package GUI;

import Cell.*;
import Grid.*;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.Map;

/**
 * simGrid
 *
 * creates GridPane object
 *
 * @author Brooke Keene, Amy Kim
 */
public class simGrid {
    private GridPane myGP;
    private BorderPane myBorder;
    private String myType;
    private int gridDim;
    private ArrayList<ArrayList<Cell>> myCells;
    private Grid myGrid;
    private Map<String, Double> myParams;

    public final double gridSpace = 500.00;

    /**
     * Constructor
     *
     * @param n, dimensions of grid
     * @param type, type of simulation to run
     * @param border, BorderPane object to add Grid to
     */
    public simGrid(int n, String type, Map<String, Double> params, BorderPane border) {
        gridDim = n;
        myType = type;
        myParams = params;

        this.choseGrid();

        myBorder = border;
        this.makeGrid();
    }

    /**
     * depending on type of simulation, creates appropriate grid
     */
    private void choseGrid() {
        if(myType.equals("Life")) {
            myGrid = new LifeGrid(gridDim);
        }
        else if(myType.equals("Fire")) {
            myGrid = new FireGrid(gridDim, myParams.get("probability"));
        }
        else if(myType.equals("Seg")) {
            myGrid = new SegGrid(gridDim, myParams.get("satisfaction").intValue(), 0.50, 0.40);
        }
//        if(myType.equals("WaTor")) {
//            myGrid = new WatorGrid(gridDim, 20, 10);
//        }
        myCells = myGrid.getGrid();
    }

    /**
     * creates GridPane object containing cells for cell automata
     */
    private void makeGrid() {
        myGP = new GridPane();
        myGP.setPrefSize(gridSpace,gridSpace);
        myGP.setMaxSize(gridSpace, gridSpace);
        for(int row = 0; row < gridDim; row++) {
            for(int col = 0; col < gridDim; col++) {
                Button temp = new Button();

                temp.setPrefSize(gridSpace/gridDim, gridSpace/gridDim);
                temp.setMaxSize(gridSpace/gridDim, gridSpace/gridDim);
                Cell tempCell = myCells.get(row).get(col);

                updateCell(tempCell, temp);

                setCellColor(tempCell, temp);

                myGP.add(temp, col, row, 1, 1);
            }
        }
        myBorder.setCenter(myGP);
    }

    /**
     *  this function helps to change a state at a grid location.
     *  If it reaches the value which is the max int of state, it becomes the state which is 0.
     */
    public void changeState(Cell tempCell){
        var user_changed = tempCell.getCurrentState() + 1;
        System.out.println("change!");
        if(tempCell.getCurrentState() == tempCell.getMaxState()){ // when it reaches the max value of states.
            tempCell.setCurrentState(0);
            return;
        }
        tempCell.setCurrentState(user_changed);

    }


    /**
     * Allow users to interact with the simulation dynamically to create or change a state at a grid location
     * @param tempCell this is Cell which the user clicked
     * @param temp now btn but will be shape later
     */
    // TO DO: change btn to shape later
   public void  updateCell(Cell tempCell, Button temp) {
       temp.setOnAction(value -> {
                   changeState(tempCell);
                   System.out.println("clicked");
                   setCellColor(tempCell, temp);
                   System.out.println("initialize");
               });
   }

    /**
     * updates simulation grid
     */
    public void updateGrid() {
        myBorder.setCenter(null);
        myGrid.updateEveryCell();
        myCells = myGrid.getGrid();
        this.makeGrid();
    }

    /**
     * chooses the color of a cell when initialized based on
     * the simulation and its initialized state
     *
     * @param tempCell
     * @param temp
     */
    private void setCellColor(Cell tempCell, Button temp) {
        if(myType.equals("Life")) {
            if(tempCell.getCurrentState() == LifeCell.DEAD) {
                temp.setId("greyCell");
            }
            else {
                temp.setId("blueCell");
            }
        }
        else if(myType.equals("Fire")) {
            if(tempCell.getCurrentState() == FireCell.TREE) {
                temp.setId("greenCell");
            }
            else if(tempCell.getCurrentState() == FireCell.FIRE) {
                temp.setId("redCell");
            }
            else {
                temp.setId("brownCell");
            }
        }
        else if(myType.equals("Seg")) {
            if(tempCell.getCurrentState() == SegregationCell.RED) {
                temp.setId("redCell");
            }
            else if(tempCell.getCurrentState() == SegregationCell.BLUE) {
                temp.setId("blueCell");
            }
            else {
                temp.setId("greyCell");
            }
        }
//        else if(myType.equals("WaTor")) {
//            if(tempCell.getCurrentState() == WaTorCell.SHARK ) {
//                temp.setId("greyCell");
//            }
//            else if(tempCell.getCurrentState() == WaTorCell.WATER) {
//                temp.setId("blueCell");
//            }
//            else {
//                temp.setId("greenCell");
//            }
//        }
        else {
            temp.setId("deadCell");
        }
    }
}
