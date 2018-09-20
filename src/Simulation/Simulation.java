package Simulation;
/**
 *
 */
public abstract class Simulation {
    private int getEmptyCells;
    private int getFreqs;
    /**
     *
     * @param row
     * @param col
     */

    public abstract void reset(int row, int col);


    /**
     *
     * @param row
     * @param col
     */
    public void start(int row, int col){

    }


    /**
     *
     * @param row
     * @param col
     */
    public void stop(int row, int col){

    }


    /**
     *
     * @param row
     * @param col
     */
    public void initializeGrid(int row, int col){

    }

}
