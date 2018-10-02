package Cell;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.paint.Color;
import Grid.Grid;
import java.util.ArrayList;
import java.util.Random;

public class RpsCell extends Cell{
    public static final int WHITE = 0;
    public static final int RED = 1;
    public static final int BLUE = 2;
    public static final int GREEN = 3;
    public static final int MAX_GRAD = 9;
    public static final int MIN_GRAD = 0;
    public static final int INCR_GRAD = 1;
    public static final int MAX_RGB = 255;
    public static final int GRAD_LEVELS = 10;
    public static final int OFFSET = 10;
    private int myGradient;
    private Random random;
    private final Map<Integer, int[]> colorMap = createMap();

    public RpsCell(int state, int x, int y){
        super(state,x,y);
        myNeighbors = new ArrayList<Cell>();
        random = new Random();
        myGradient = MIN_GRAD;
    }

    private Map<Integer, int[]> createMap() {
        Map<Integer,int[]> myMap = new HashMap<>();
        myMap.put(RED, new int[]{GREEN,BLUE});//red gets eaten by green, eats blue
        myMap.put(GREEN, new int[]{BLUE, RED});//green gets eaten by blue, eats red
        myMap.put(BLUE, new int[]{RED,GREEN});//same pattern
        return myMap;
    }

    public void checkCells(Grid g) {
        myNeighbors = g.getCellsNear(this);
        int choice = random.nextInt(myNeighbors.size());
        Cell neighbor = myNeighbors.get(choice);

        if (currentState == WHITE && ((RpsCell) neighbor).getGradient() < MAX_GRAD) {
            nextState = neighbor.getCurrentState();
            myGradient = ((RpsCell) neighbor).getGradient() + INCR_GRAD;
        } else if (currentState != WHITE) {
            play(currentState, neighbor);
        }
    }


    private void play(int state, Cell neighbor){
        if(neighbor.getCurrentState() == colorMap.get(state)[0]){
            nextState = colorMap.get(state)[0];
            myGradient = ((RpsCell)neighbor).getGradient();
        } else if (neighbor.getCurrentState() == colorMap.get(state)[1] && myGradient > MIN_GRAD){
            myGradient--;//get darker after 'eating' another cell
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
        double amt = MAX_RGB;
        int val = (int)((GRAD_LEVELS-myGradient) * amt/(GRAD_LEVELS+OFFSET));
        if(currentState == WHITE){
            myColor = Color.WHITE;
        } else  if(currentState == RED){
            myColor = Color.rgb(MAX_RGB-val,MIN_GRAD,MIN_GRAD);
        } else if(currentState == GREEN){
            myColor = Color.rgb(MIN_GRAD,MAX_RGB-val,MIN_GRAD);
        } else{
            myColor = Color.rgb(MIN_GRAD,MIN_GRAD,MAX_RGB-val);
        }
        return myColor;
    }




}
