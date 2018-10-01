package GUI;

import Cell.*;
import Grid.*;
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
                Cell tempCell = myCells.get(row).get(col);

                updateCell(tempCell, r);
                r.setFill(tempCell.getColor());
                r.setStroke(Color.WHITE);


                myGP.add(r, col, row, 1, 1);
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
        if(tempCell.getCurrentState() == tempCell.getMaxState()){ // when it reaches the max value of states.
            tempCell.setCurrentState(0);
            tempCell.setNextState(0);
            return;
        }
        tempCell.setCurrentState(user_changed);
        tempCell.setNextState(user_changed);

    }

    /**
     * Allow users to interact with the simulation dynamically to create or change a state at a grid location
     * @param tempCell this is Cell which the user clicked
     * @param r now btn but will be shape later
     */
    public void  updateCell(Cell tempCell, Rectangle r) {
        r.setOnMouseClicked(value -> {
            if(myType.equals("WaTor")){
                ((WatorCell)tempCell).resetEnergyAndBreed();
            }
            changeState(tempCell);
            r.setFill(tempCell.getColor());
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



}