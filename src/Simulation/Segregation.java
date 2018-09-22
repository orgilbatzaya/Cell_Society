package Simulation;

import Simulation.Simulation;
import Cell.Cell;
import Grid.Grid;
import java.util.List;

/*
@author ob29
 */

public class Segregation extends Simulation {
    private List<List<Cell>> myCells;
    private Grid myGrid;
    private int size;
    private double similarity;
    private double ratio;
    private double empty;


    public Segregation(int size, double similarity, double ratio, double empty){
        this.size = size;
        this.similarity = similarity;
        this.ratio = ratio;
        this.empty = empty;

    }

    public void initializeGrid(int row,int col){

    }
}
