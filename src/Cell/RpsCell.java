package Cell;

import javafx.scene.paint.Color;
import Grid.Grid;
import java.util.ArrayList;
import java.util.Random;

public class RpsCell extends Cell{
    public static final int WHITE = 0;
    public static final int RED = 1;
    public static final int BLUE = 2;
    public static final int GREEN = 3;
    private int MAX_GRADIENT = 9;
    private int myGradient = 0;
    private Random random;

    public RpsCell(int state, int x, int y){
        super(state,state,x,y);
        myNeighbors = new ArrayList<Cell>();
        random = new Random();
        myGradient = 0;

    }

    public void checkCells(Grid g){
        myNeighbors = g.getCellsNear(this);
        int choice = random.nextInt(myNeighbors.size());
        Cell neighbor = myNeighbors.get(choice);
//        if(myGradient < 0){
//            myGradient = 0;
//        }

        if(currentState == WHITE && ((RpsCell) neighbor).getGradient() < MAX_GRADIENT){
            nextState = neighbor.getCurrentState();
            myGradient = ((RpsCell) neighbor).getGradient() + 1;
        } else if (currentState == RED){
            redOp(neighbor);
        } else if (currentState == GREEN){
            greenOp(neighbor);
        } else if (currentState == BLUE){
            blueOp(neighbor);
        }
    }

    private void redOp(Cell neighbor){
        if(neighbor.getCurrentState() == GREEN){
            nextState = GREEN;
            myGradient = ((RpsCell)neighbor).getGradient();
        } else if (neighbor.getCurrentState() == BLUE && myGradient > 0){
            myGradient--;
        }
    }

    private void greenOp(Cell neighbor){
        if(neighbor.getCurrentState() == BLUE){
            nextState = BLUE;
            myGradient = ((RpsCell)neighbor).getGradient();
        } else if (neighbor.getCurrentState() == RED && myGradient > 0){
            myGradient--;
        }
    }

    private void blueOp(Cell neighbor){
        if(neighbor.getCurrentState() == RED){
            nextState = RED;
            myGradient = ((RpsCell)neighbor).getGradient();
        } else if (neighbor.getCurrentState() == GREEN && myGradient > 0){
            myGradient--;
        }
    }

    private int getGradient(){
        return myGradient;
    }

    @Override
    public int getMaxState() {
        return GREEN;
    }

    public Color getColor(){
        double amt = 255;
        int val = (int)((10 - myGradient) * amt/10);
        if(currentState == WHITE){
            myColor = Color.WHITE;
        } else  if(currentState == RED){
            myColor = Color.rgb(255-val,255,255);
        } else if(currentState == GREEN){
            myColor = Color.rgb(255,255-val,255);
        } else{
            myColor = Color.rgb(255,255,255-val);
        }
        return myColor;
    }




}
