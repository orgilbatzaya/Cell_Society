package GUI;

import Cell.*;
import Grid.*;
import javafx.scene.layout.BorderPane;
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
        myPane = new AnchorPane();

//        // Square Grid
//        for(int row = 0; row < gridDim; row++) {
//            for (int col = 0; col < gridDim; col++) {
//                Cell tempCell = myCells.get(row).get(col);
//                Polygon p = makeSquare(row, col);//gc, tempCell);
//                p.setFill(tempCell.getColor());
//                p.setStroke(Color.WHITE);
//                updateCell(tempCell, p);
//                myPane.getChildren().add(p);
//            }
//        }

        // Triangle Grid
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

//        // Hexagonal Grid - NOT WORKING
//        for(int row = 0; row < gridDim; row++) {
//            for (int col = 0; col < gridDim; col++) {
//                Cell tempCell = myCells.get(row).get(col);
//                if(col%2 == 0) {
//                    makeEvenHexagon(row, col, gc, tempCell);
//                }
//                else {
//                    makeOddHexagon(row, col, gc, tempCell);
//                }
//            }
//        }

        myBorder.setCenter(myPane);
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

    private void colorCell(Polygon p, Cell tempCell) {
        p.setFill(tempCell.getColor());
        p.setStroke(Color.WHITE);
        updateCell(p, tempCell);
    }

//    /** NOT WORKING
//     * draws individual hexagons for grid
//     *
//     * @param r
//     * @param c
//     * @param gc
//     * @param temp
//     */
//    private void makeEvenHexagon(int r, int c, GraphicsContext gc, Cell temp){
//        double width = gridSpace/gridDim;
//        double[] xPts = new double[]{1.0*r*width, 1.0*r*width+width/2, 1.0*r*width+width, 1.0*r*width+width, 1.0*r*width+width/2, 1.0*r*width};
//        double[] yPts = new double[]{1.0*c*width+width/4, 1.0*c*width, 1.0*c*width+width/4, 1.0*c*width+width/4+width/2, 1.0*c*width+width, 1.0*c*width+width/4+width/2};
//
//
//        gc.fillPolygon(xPts, yPts, 6);
//        gc.setFill(temp.getColor());
//        gc.setStroke(Color.WHITE);
//        gc.strokePolygon(xPts, yPts, 6);
//    }
//
//    /**
//     * draws individual hexagons for grid
//     * xPts shifted by + width/2
//     * yPts shifted by - width/4
//     *
//     * @param r
//     * @param c
//     * @param gc
//     * @param temp
//     */
//    private void makeOddHexagon(int r, int c, GraphicsContext gc, Cell temp){
//        double width = gridSpace/gridDim;
//        double[] xPts = new double[]{1.0*r*width+width/2, 1.0*r*width+width, 1.0*r*width+width+width/2, 1.0*r*width+width+width/2, 1.0*r*width+width, 1.0*r*width+width/2};
//        double[] yPts = new double[]{1.0*c*width, 1.0*c*width-width/4, 1.0*c*width, 1.0*c*width+width/2, 1.0*c*width+3*width/4, 1.0*c*width+width/2};
//
//
//        gc.fillPolygon(xPts, yPts, 6);
//        gc.setFill(temp.getColor());
//        gc.setStroke(Color.WHITE);
//        gc.strokePolygon(xPts, yPts, 6);
//    }


    /**
     *  this function helps to change a state at a grid location.
     *  If it reaches the value which is the max int of state, it becomes the state which is 0.
     */
    public void changeState(Cell tempCell){
        var user_changed = tempCell.getCurrentState() + 1;
        if(tempCell.getCurrentState() == tempCell.getMaxState()){ // when it reaches the max value of states.
            tempCell.setCurrentState(0);
            return;
        }
        tempCell.setCurrentState(user_changed);

    }

    /**
     * Allow users to interact with the simulation dynamically to create or change a state at a grid location
     * @param tempCell this is Cell which the user clicked
     * @param p now btn but will be shape later
     */
    public void  updateCell(Polygon p, Cell tempCell) {
        p.setOnMousePressed(value -> {
            changeState(tempCell);
            p.setFill(tempCell.getColor());
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