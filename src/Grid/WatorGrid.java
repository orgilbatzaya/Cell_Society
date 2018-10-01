/**
 * @author Orgil Batzaya
 */

package Grid;
import Cell.Cell;
import Cell.WatorCell;
import java.util.*;

/**
 * WaTor Grid
 */
public class WatorGrid extends Grid {
    private int energy, energySaved;
    private int breedingTime, breedingTimeSaved;
    private double sharkRatio;
    private double emptyRatio;

    public WatorGrid(int size, double sharkRatio, double emptyRatio, int breedingTime, int sharkEnergy) {
        super(size);
        this.energy = sharkEnergy;
        this.breedingTime = breedingTime;
        this.breedingTimeSaved = breedingTime;
        this.energySaved = energy;
        this.sharkRatio = sharkRatio;
        this.emptyRatio = emptyRatio;
        myCells = new ArrayList<ArrayList<Cell>>();
        initializeCells();
    }

    public void initializeCells(){
        var states = randomizeStates();
        for(int i = 0; i < size; i++){
            var row = new ArrayList<Cell>();
            for(int j = 0; j < size; j++){
                int state = states.pop();
                Cell cell = new WatorCell(state, i, j, breedingTime, energy, breedingTimeSaved, energySaved);
                row.add(cell);
            }
            myCells.add(row);
        }
    }

    public Stack<Integer> randomizeStates(){
        var animals = new Stack<Integer>();
        int numEmpty = (int) (emptyRatio*size*size);
        int numShark = (int) (sharkRatio*(size*size - numEmpty));
        int numFish = (size*size) - numEmpty - numShark;
        for(int i = 0; i < numEmpty; i++){
            animals.add(WatorCell.WATER);
        }
        for(int i = 0; i < numShark; i++){
            animals.add(WatorCell.SHARK);
        }
        for(int i = 0; i < numFish; i++){
            animals.add(WatorCell.FISH);
        }
        Collections.shuffle(animals);
        return animals;
    }

    @Override
    public List<Cell> getCellsNear(Cell cell){
        List<Cell> nearCells = new ArrayList<Cell>();
        List<int[]> positions = getNearCellPositions(cell);
        for(int[] pos:positions) {
            if (pos[0] < 0) {
                pos[0] = size - 1;
            }
            if (pos[0] >= size) {
                pos[0] = 0;
            }
            if (pos[1] < 0) {
                pos[1] = size - 1;
            }
            if (pos[1] >= size) {
                pos[1] = 0;
            }
            nearCells.add(myCells.get(pos[0]).get(pos[1]));
        }
        return nearCells;
    }

    /**
     * Given some Cell in the Grid, finds and stores all (4) adjacent positions
     * regardless of whether these positions are bounded
     * @param cell Cell object
     * @return List of int arrays
     */
    @Override
    public List<int[]> getNearCellPositions(Cell cell) {
        List<int[]> positions = new ArrayList<>();
        int xPos = cell.getX();
        int yPos = cell.getY();

        if(inBounds(cell.getX(), cell.getY())){
            positions.add(new int[]{xPos -1, yPos});//sides
            positions.add(new int[]{xPos +1, yPos});
            positions.add(new int[]{xPos, yPos-1});
            positions.add(new int[]{xPos, yPos+1});
        }
        return positions;
    }

    @Override
    public  void updateEveryCell(){
        untakeCells();
        var aliveCells = getAliveCells();
        for(Cell cell: aliveCells) {
            ((WatorCell) cell).getNeighbors(this);
            ((WatorCell) cell).move();
            if (((WatorCell) cell).isMoving()) {
                reposition(cell);
            }
            if(((WatorCell) cell).isBirthing()){
                birth(cell);
            }
            ((WatorCell) cell).unMoving();
            ((WatorCell) cell).unBirthing();
            ((WatorCell) cell).clearNeighbors();
        }
        updateStates();
        System.out.println(Arrays.toString(getStats()));
    }

    public void reposition(Cell cell){
        int[] newPos = ((WatorCell) cell).getNextPos();
        int state = cell.getCurrentState();
        Cell replacement = new WatorCell(state, newPos[0], newPos[1],
                ((WatorCell) cell).getBreedingTime(), ((WatorCell) cell).getEnergy(),
                breedingTimeSaved, energySaved);
        myCells.get(newPos[0]).set(newPos[1], replacement);
    }

    public void birth(Cell cell){
        int[] babyPos = ((WatorCell) cell).getBabyPos();
        int state = cell.getCurrentState();
        ((WatorCell) cell).resetEnergyAndBreed();
        Cell replacement = new WatorCell(state, babyPos[0], babyPos[1],
                ((WatorCell) cell).getBreedingTime(), ((WatorCell) cell).getEnergy(),
                breedingTimeSaved, energySaved);
        myCells.get(babyPos[0]).set(babyPos[1], replacement);
    }

    public List<Cell> getAliveCells(){
        var sharkCells = getRequiredCells(WatorCell.SHARK);
        var FishCells = getRequiredCells(WatorCell.FISH);
        Collections.shuffle(sharkCells);
        Collections.shuffle(sharkCells);
        var aliveCells = new ArrayList<Cell>();
        aliveCells.addAll(sharkCells);
        aliveCells.addAll(FishCells);
        return aliveCells;
    }

    @Override
    public double[] getStats(){
        int f, s, w;
        f = s = w = 0;
        for(int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (myCells.get(x).get(y).getCurrentState() == WatorCell.FISH) {
                    f++;
                } else if (myCells.get(x).get(y).getCurrentState() == WatorCell.SHARK) {
                    s++;
                } else {
                    w++;
                }
            }
        }
        return new double[]{(double)f/(size*size),(double)s/(size*size),(double)w/(size*size)};
    }

    public void untakeCells(){
        for(ArrayList<Cell> row: myCells){
            for(Cell t:row){
                ((WatorCell) t).unTaken(); //at each step, "untake" taken cells
            }
        }
    }
    public void checkNeighbors(Cell cell){

    }


}