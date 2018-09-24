/**
 * @author Amy Kim
 */

package Cell;

import Cell.Cell;
import Grid.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SharkCell{

    public int[] move(List<int[]> positions, int cnt) {
        var random = new Random();
        int[] nextLoc = positions.get(random.nextInt(cnt-1)); //store where this shark will move to
        return nextLoc;
    }

    public boolean dead(int energy, Grid g){
        return (energy == 0) ? true: false;
    }

}