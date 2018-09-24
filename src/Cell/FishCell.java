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



public class FishCell {

    public int[] move(List<int[]> positions, int cnt) {
        var random = new Random();
        int[] nextLoc = positions.get(random.nextInt(cnt)); //store where this fish will move to
        return nextLoc;
    }
}
