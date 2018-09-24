package GUI;

<<<<<<< HEAD
import Cell.*;
import Grid.*;
=======
import Cell.Cell;
import Cell.LifeCell;
import Cell.FireCell;
import Cell.SegregationCell;
import Grid.LifeGrid;
import Grid.FireGrid;
import Grid.SegGrid;
>>>>>>> a89239736821d91a307afbfa67e0e8fbaa7fd13f
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
<<<<<<< HEAD
    private ArrayList<ArrayList<Cell>> myCells;
    private Grid myGrid;
=======

    private ArrayList<ArrayList<LifeCell>> myCells;
    //TODO: figure out why just Grid doesn't work
    private LifeGrid myLifeGrid;
>>>>>>> a89239736821d91a307afbfa67e0e8fbaa7fd13f


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
        if(myType.equals("Life")) {
            myGrid = new LifeGrid(gridDim);
        }
        else if(myType.equals("Fire")) {
            myGrid = new FireGrid(gridDim, 0.7);
        }
        else if(myType.equals("Seg")) {
            myGrid = new SegGrid(gridDim, 60, 0.5, 0.3);
        }
//        if(myType.equals("wat")) {
//            myLifeGrid = WatorGrid();
//        }
<<<<<<< HEAD
        myCells = myGrid.getGrid();
=======
//        if(myType.equals("fire")) {
//            myLifeGrid = new FireGrid();
//        }
        //myLifeGrid = new LifeGrid(gridDim);
        //myCells = myLifeGrid.getGrid();

        //myFireGrid = new FireGrid(gridDim, .7);
        //myCells = myFireGrid.getGrid();

        mySegGrid = new SegGrid(gridDim, 60, .50, .40);
        myCells = mySegGrid.getGrid();
>>>>>>> a89239736821d91a307afbfa67e0e8fbaa7fd13f
    }

    // TODO: make it more general
    private void makeGrid() {
        myGP = new GridPane();
        myGP.setPrefSize(gridSpace,gridSpace);
        myGP.setMaxSize(gridSpace, gridSpace);
        for(int row = 0; row < gridDim; row++) {
            for(int col = 0; col < gridDim; col++) {
                Button temp = new Button();
<<<<<<< HEAD
                temp.setPrefSize(gridSpace/gridDim, gridSpace/gridDim);
                temp.setMaxSize(gridSpace/gridDim, gridSpace/gridDim);
                Cell tempCell = myCells.get(row).get(col);

                setCellColor(tempCell, temp);
=======
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

>>>>>>> a89239736821d91a307afbfa67e0e8fbaa7fd13f

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
<<<<<<< HEAD
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
//        else if(myType.equals("WaTor")) {
//            if(tempCell.getCurrentState() == ) {
//                temp.setId("redCell");
//            }
//            else if(tempCell.getCurrentState() == ) {
//                temp.setId("blueCell");
//            }
//            else {
//                temp.setId("greyCell");
//            }
//        }
        else {
            temp.setId("deadCell");
        }
=======
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
>>>>>>> a89239736821d91a307afbfa67e0e8fbaa7fd13f
    }
}
