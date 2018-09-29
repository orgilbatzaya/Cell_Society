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

    /**
     * Constructor
     *
     * @param n, dimensions of grid
     * @param type, type of simulation to run
     * @param border, BorderPane object to add Grid to
     */
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
            myGrid = new SegGrid(gridDim, 100, 0.50, 0.40);
        }
        if(myType.equals("WaTor")) {
            myGrid = new WatorGrid(gridDim, .25, .40, 2, 2);
        }
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
        else if(myType.equals("WaTor")) {
            if(tempCell.getCurrentState() == FishCell.FISH ) {
                temp.setId("blueCell");
            }
            else if(tempCell.getCurrentState() == FishCell.WATER) {
                temp.setId("greyCell");
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
