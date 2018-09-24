package GUI;

import Cell.Cell;
import Cell.LifeCell;
import Cell.FireCell;

import Cell.SegregationCell;
import Grid.LifeGrid;
import Grid.FireGrid;
import Grid.SegGrid;
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


    private ArrayList<ArrayList<LifeCell>> myCells;
    //TODO: figure out why just Grid doesn't work
    private LifeGrid myLifeGrid;
    private FireGrid myFireGrid;


    //private LifeGrid myLifeGrid;
    //private FireGrid myFireGrid;
    private SegGrid mySegGrid;


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
//        if(myType.equals("life")) {
//            myLifeGrid = new LifeGrid(gridDim);
//        }
//        if(myType.equals("seg")) {
//            myLifeGrid = new SegGrid();
//        }
//        if(myType.equals("wat")) {
//            myLifeGrid = WatorGrid();
//        }
//        if(myType.equals("fire")) {
//            myLifeGrid = new FireGrid();
//        }

        //myLifeGrid = new LifeGrid(gridDim);
        //myCells = myLifeGrid.getGrid();

        //myFireGrid = new FireGrid(gridDim, .7);
        //myCells = myFireGrid.getGrid();

        mySegGrid = new SegGrid(gridDim, 60, .50, .40);
        myCells = mySegGrid.getGrid();

    }

    // TODO: make it more general
    private void makeGrid() {
        myGP = new GridPane();
        myGP.setPrefSize(gridSpace,gridSpace);
        myGP.setMaxSize(gridSpace, gridSpace);
        for(int row = 0; row < gridDim; row++) {
            for(int col = 0; col < gridDim; col++) {
                Button temp = new Button();
                //temp.setPrefHeight(gridSpace/gridDim);
                //temp.setPrefWidth(gridSpace/gridDim);
                temp.setPrefSize(gridSpace/gridDim, gridSpace/gridDim);
                temp.setMaxSize(gridSpace/gridDim,gridSpace/gridDim);
                Cell tempCell = myCells.get(row).get(col);
                tempCell.getCurrentState();

                if(tempCell.getCurrentState() == 0) {
                    temp.setId("deadCell");
                }
                else if(tempCell.getCurrentState() == 1){
                    temp.setId("aliveCell");
                }
                else if(tempCell.getCurrentState() == 2){
                    temp.setId("otherCell");
                }



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
        //myLifeGrid.updateEveryCell();
        //myCells = myLifeGrid.getGrid();


        //myFireGrid.updateEveryCell();
        //myCells = myFireGrid.getGrid();

        mySegGrid.updateEveryCell();
        myCells = mySegGrid.getGrid();

        this.makeGrid();
    }

    /**
     * resets simulation grid
     */
    public void resetGrid() {


        myLifeGrid.reset();
        //TODO: does this actually reset? or just change the simulation?
        updateGrid();
    }
}
