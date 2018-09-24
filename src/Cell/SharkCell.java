/**
 * @author Amy Kim
 */

package Cell;

import Cell.Cell;
import Grid.Grid;
import Grid.WatorGrid;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SharkCell{

    public int[] move(List<int[]> positions, int cnt) {
        var random = new Random();
        int[] nextLoc = positions.get(random.nextInt(cnt-1)); //store where this shark will move to
        return nextLoc;
    }


}