package Simulation;

import Grid.Grid;

import java.util.Timer;
import java.util.TimerTask;

public class Simulation {
    private Grid grid;
    private Timer timer;
    private boolean playing;
    private long interval; // in milliseconds

    public Simulation(int row, int col, long interval) {
        this.interval = interval;
        timer = new Timer();
        playing = false;
        initializeGrid(row, col);
    }

    public void setInterval(long newInterval) {
        interval = newInterval;
        if(playing) { stop(); start(); }
    }

    /**
     * @param row
     * @param col
     */
    public void initializeGrid(int row, int col){
        // instantiate new grid of (row, col)
    }


    public void start() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() { step(); }
        }, 0, interval);
    }
    public void stop(){ timer.cancel(); }
    public void step() { grid.updateEveryCell(); }

}
