package GUI;

import Cell.*;
import Grid.*;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.Map;

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
        if(myType.equals("WaTor")) {
            myGrid = new WatorGrid(gridDim, .20, .10, 5, 3 );
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
                Rectangle r = new Rectangle();
                r.setX(col);
                r.setY(row);
                r.setWidth(gridSpace/gridDim);
                r.setHeight(gridSpace/gridDim);
//                Button temp = new Button();

//                temp.setPrefSize(gridSpace/gridDim, gridSpace/gridDim);
//                temp.setMaxSize(gridSpace/gridDim, gridSpace/gridDim);
                Cell tempCell = myCells.get(row).get(col);

                setCellColor(tempCell, r);


                myGP.add(r, col, row, 1, 1);
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
     * @param r
     */
    private void setCellColor(Cell tempCell, Rectangle r) {
        r.setStroke(Color.WHITE);
        if(myType.equals("Life")) {
            if(tempCell.getCurrentState() == LifeCell.DEAD) {
                r.setFill(Color.GRAY);
            }
            else {
                r.setFill(Color.BLUE);
            }
        }
        else if(myType.equals("Fire")) {
            if(tempCell.getCurrentState() == FireCell.TREE) {
                r.setFill(Color.GREEN);
            }
            else if(tempCell.getCurrentState() == FireCell.FIRE) {
                r.setFill(Color.RED);
            }
            else {
                r.setFill(Color.BROWN);
            }
        }
        else if(myType.equals("Seg")) {
            if(tempCell.getCurrentState() == SegregationCell.RED) {
                r.setFill(Color.RED);
            }
            else if(tempCell.getCurrentState() == SegregationCell.BLUE) {
                r.setFill(Color.BLUE);
            }
            else {
                r.setFill(Color.GRAY);
            }
        }
        else if(myType.equals("WaTor")) {
            if(tempCell.getCurrentState() == WatorCell.FISH ) {
                temp.setId("blueCell");
            }
            else if(tempCell.getCurrentState() == WatorCell.WATER) {
                temp.setId("greyCell");
            }
            else {
                temp.setId("greenCell");
            }
        }

        else {
            r.setFill(Color.GRAY);
        }
    }
}
