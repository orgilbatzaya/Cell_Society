package GUI;

import Cell.Cell;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.List;

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
    private int gridDim;
    private List<List<Cell>> myGrid;

    private double gridSpace = 500.00;

    public simGrid(int n, BorderPane border) {
        gridDim = n;
        myBorder = border;
        this.makeGrid();
    }

    public void makeGrid() {
        myGP = new GridPane();
        myGP.setPrefSize(gridSpace, gridSpace);

        for(int row = 0; row < gridDim; row++) {
            for(int col = 0; col < gridDim; col++) {
                Button temp = new Button();
                temp.setPrefHeight(gridSpace/gridDim);
                temp.setPrefWidth(gridSpace/gridDim);
                temp.setId("cellBtn");
                myGP.add(temp, col, row, 1, 1);
            }
        }
        myBorder.setCenter(myGP);
    }
}
