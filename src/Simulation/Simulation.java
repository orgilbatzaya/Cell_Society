/*
@author yk154
 */

package Simulation;

import GUI.simGrid;

public class Simulation {
    private simGrid grid;
    private boolean playing;
    private double interval; // in seconds
    private double timer;

    public Simulation(simGrid grid, double interval) {
        this.grid = grid;
        this.interval = interval;
        this.timer = 0;
        playing = false;
    }

    public void setInterval(long newInterval) {
        interval = newInterval;
        if(playing) { stop(); start(); }
    }

    public void ticktock(double duration) {
        if(playing) {
            timer += duration;
            if(interval < timer) {
                timer = 0;
                step();
            }
        }
    }

    public void start() { playing = true; }
    public void stop(){ playing = false; timer = 0; }
    public void step() { grid.updateGrid(); }

}