package GUI;

import Cell.*;
import Grid.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
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
    private AnchorPane myPane;
    private int gridDim;
    private String gridShape;
    private String gridEdge;
    private String myType;
    private ArrayList<ArrayList<Cell>> myCells;
    private Grid myGrid;
    private Map<String, Double> myParams;

    public final double gridSpace = 500.00;

    /**
     * Constructor
     *
     * @param n dimensions of grid
     * @param shape shape of grid (square, triangle, hexagonal)
     * @param edge edge type of grid (finite, toroidal, infinite)
     * @param type type of simulation to run
     * @param params simulation parameters
     */
    public simGrid(int n, String shape, String edge, String type, Map<String, Double> params) {
        gridDim = n;
        gridShape = shape;
        gridEdge = edge;
        myType = type;
        myParams = params;

        this.choseGrid();

        myPane = new AnchorPane();
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
        else if(myType.equals("WaTor")) {
            myGrid = new WatorGrid(gridDim, .20, .10, 5, 3 );
        }
        else if(myType.equals("RPS")) {
            myGrid = new RpsGrid(gridDim);
        }
        myCells = myGrid.getGrid();
    }

    /**
     * creates AnchorPane object containing cells for the cell automata
     */
    public AnchorPane makeGrid() {
        if(gridShape.equals("Square")) {
            makeSquareGrid();
        }
        else if(gridShape.equals("Triangle")) {
            makeTriangleGrid();
        }
        else {
            makeSquareGrid(); // would have been makeHexagonalGrid() had we had time
        }
        return myPane;
    }

    /**
     * creates square grid by looping through myCells
     */
    private void makeSquareGrid() {
        for(int row = 0; row < gridDim; row++) {
            for (int col = 0; col < gridDim; col++) {
                Cell tempCell = myCells.get(row).get(col);
                Polygon p = makeSquare(row, col, tempCell);
                p.setFill(tempCell.getColor());
                p.setStroke(Color.WHITE);
                updateCell(p, tempCell);
                myPane.getChildren().add(p);
            }
        }
    }

    /**
     * creates triangle grid by looping through myCells
     */
    private void makeTriangleGrid() {
        for(int row = gridDim-1; row >= 0; row--) {
            for(int col = row; col >= 0; col--) {
                Cell tempCell = myCells.get(row).get(col);

                if(col == row || row == 0) {
                    Polygon p = makeTopTriangle(row, col, tempCell);

                    myPane.getChildren().add(p);
                }
                else {
                    Polygon p = makeTopTriangle(row, col, tempCell);
                    Polygon p2 = makeBotTriangle(row, col, tempCell);

                    myPane.getChildren().addAll(p,p2);
                }

            }
        }
    }

    /**
     * draws individual squares for grid
     *
     * @param r
     * @param c
     */
    private Polygon makeSquare(int r, int c, Cell temp) {
        double width = gridSpace/gridDim;
        Polygon p = new Polygon();
        p.getPoints().addAll(new Double[] {
                1.0*r*width, 1.0*c*width,
                1.0*r*width, 1.0*c*width+width,
                1.0*r*width+width, 1.0*c*width+width,
                1.0*r*width+width, 1.0*c*width});
        colorCell(p, temp);
        return p;
    }

    /**
     * draws individual upside-down triangles for grid
     *
     * @param r
     * @param c
     */
    private Polygon makeTopTriangle(int r, int c, Cell temp) {
        double width = gridSpace/gridDim;
        Polygon p = new Polygon();
        p.getPoints().addAll(new Double[] {
                1.0*r*width-(c*width/2), 1.0*c*width,
                1.0*r*width+width/2-(c*width/2), 1.0*c*width+width,
                1.0*r*width+width-(c*width/2), 1.0*c*width});
        colorCell(p, temp);
        return p;
    }

    /**
     * draws individual normal triangles for grid
     *
     * @param r
     * @param c
     */
    private Polygon makeBotTriangle(int r, int c, Cell temp) {
        double width = gridSpace/gridDim;
        Polygon p = new Polygon();
        p.getPoints().addAll(new Double[] {
                1.0*r*width-width/2-(c*width/2), 1.0*c*width+width,
                1.0*r*width-(c*width/2), 1.0*c*width,
                1.0*r*width+width/2-(c*width/2), 1.0*c*width+width});
        colorCell(p, temp);
        return p;
    }

    /**
     * sets color and outline of polygon based on the
     * cell's state and sets on click functionality
     *
     * @param p
     * @param tempCell
     */
    private void colorCell(Polygon p, Cell tempCell) {
        p.setFill(tempCell.getColor());
        p.setStroke(Color.BLACK);
        updateCell(p, tempCell);
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
     * @param p now btn but will be shape later
     */
    public void  updateCell(Polygon p, Cell tempCell) {
        p.setOnMousePressed(value -> {
            if(myType.equals("WaTor")){ //For WaTor, when its' states changes, it should reset Energy and Breed.
                ((WatorCell)tempCell).resetEnergyAndBreed();
            }
            changeState(tempCell);
            p.setFill(tempCell.getColor());
        });
    }

    /**
     * updates simulation grid
     */
    public AnchorPane updateGrid() {
        myGrid.updateEveryCell();
        myCells = myGrid.getGrid();
        return this.makeGrid();
    }



}