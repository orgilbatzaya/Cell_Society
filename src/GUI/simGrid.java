package GUI;

import Cell.*;
import Grid.*;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;

/**
 * simGrid
 *
 * creates GridPane object
 *
 * @author Brooke Keene
 */
public class simGrid {
    private GridPane myGP;
    private BorderPane myBorder;
    private String myType;
    private int gridDim;
    private ArrayList<ArrayList<Cell>> myCells;
    private Grid myGrid;


    public final double gridSpace = 500.00;

    public simGrid(int n, String type, BorderPane border) {
        gridDim = n;
        myType = type;

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
            myGrid = new FireGrid(gridDim, 0.7);
        }
        else if(myType.equals("Seg")) {
            myGrid = new SegGrid(gridDim, 60, 0.50, 0.40);
        }
        if(myType.equals("WaTor")) {
            myGrid = new WatorGrid(gridDim, 20, 10);
        }
        myCells = myGrid.getGrid();
    }

    // TODO: make it more general
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

                setCellColor(tempCell, temp);


                myGP.add(temp, col, row, 1, 1);
            }
        }
        myBorder.setCenter(myGP);
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

//    /**
//     * resets simulation grid
//     */
//    public void resetGrid() {
//        myGrid.reset();
//        //TODO: does this actually reset? or just change the simulation?
//        updateGrid();
//    }

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
        else if(myType.equals("WaTor")) {
            if(tempCell.getCurrentState() == WaTorCell.SHARK ) {
                temp.setId("greyCell");
            }
            else if(tempCell.getCurrentState() == WaTorCell.WATER) {
                temp.setId("blueCell");
            }
            else {
                temp.setId("greenCell");
            }
        }
        else {
            temp.setId("deadCell");
        }
    }
}
