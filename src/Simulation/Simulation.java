/*
@author Amy Kim
 */

package Simulation;

public class Simulation {
    private boolean playing;
    private double timer;

    public Simulation() {
        timer = 0;
        playing = false;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void incrementTimer(double duration) {
        timer += duration;
    }

    public double getTimer() {
        return timer;
    }

    public void resetTimer() {
        timer = 0;
    }

    public void start() {
        playing = true;
    }

    public void stop(){
        playing = false;
        timer = 0;
    }
}