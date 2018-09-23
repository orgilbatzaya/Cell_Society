package GUI;

import Cell.Cell;
import Cell.LifeCell;
import Cell.FireCell;
import Grid.LifeGrid;
import Grid.FireGrid;
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
    //private ArrayList<ArrayList<LifeCell>> myCells;
    private ArrayList<ArrayList<FireCell>> myCells;

    private LifeGrid myLifeGrid;
    private FireGrid myFireGrid;

    private double gridSpace = 500.00;

    public simGrid(int n, String type, BorderPane border) {
        gridDim = n;
        myType = type;

        this.choseGrid();

        myBorder = border;
        this.makeGrid();
    }

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
        //myLifeGrid= new LifeGrid(gridDim);
        //myCells = myLifeGrid.getGrid();

        myFireGrid= new FireGrid(gridDim, .7);
        myCells = myFireGrid.getGrid();
    }

    // TODO: make it more general?????
    private void makeGrid() {
        myGP = new GridPane();
        myGP.setPrefSize(gridSpace, gridSpace);
        for(int row = 0; row < gridDim; row++) {
            for(int col = 0; col < gridDim; col++) {
                Button temp = new Button();
                temp.setPrefHeight(gridSpace/gridDim);
                temp.setPrefWidth(gridSpace/gridDim);
                Cell tempCell = myCells.get(row).get(col);
                tempCell.getCurrentState();
//                if(tempCell.getCurrentState() == LifeCell.DEAD) {
//                    temp.setId("deadCell");
//                }
//                else {
//                    temp.setId("aliveCell");
//                }
                if(tempCell.getCurrentState() == 0){
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


    public void updateGrid() {
        myBorder.setCenter(null);
        //myLifeGrid.updateEveryCell();
        //myCells = myLifeGrid.getGrid();
        myFireGrid.updateEveryCell();
        myCells = myFireGrid.getGrid();

        this.makeGrid();
    }

    public void resetGrid() {
        //myLifeGrid.reset();
        myFireGrid.reset();
        updateGrid();
    }
}
