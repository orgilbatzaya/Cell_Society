/*
@author Amy Kim
 */

package Simulation;

/**
 * This is Simulation class for handling the buttons and Timer
 *
 * @author Amy Kim
 * @author Brooke Keene
 */
public class Simulation {
    private boolean playing;
    private double timer;

    /**
     * Constructor
     */
    public Simulation() {
        timer = 0;
        playing = false;
    }

    /**
     * returns a boolean that communicates whether simulation should be running
     *
     * @return boolean
     */
    public boolean isPlaying() {
        return playing;
    }

    /**
     * increments timer variable
     *
     * @param duration
     */
    public void incrementTimer(double duration) {
        timer += duration;
    }

    /**
     * returns the value of timer
     *
     * @return double
     */
    public double getTimer() {
        return timer;
    }

    /**
     * resets timer variable to zero
     */
    public void resetTimer() {
        timer = 0;
    }

    /**
     * sets boolean playing to true
     */
    public void start() {
        playing = true;
    }

    /**
     * sets boolean playing to false and
     * resets timer variable to zero
     */
    public void stop(){
        playing = false;
        timer = 0;
    }
}