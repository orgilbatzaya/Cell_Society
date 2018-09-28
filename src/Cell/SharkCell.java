///**
// * @author Amy Kim
// */
//
//package Cell;
//
//import Grid.*;
//import java.util.List;
//import java.util.Random;
//
//
///**
// * SharkCell Class for choosing random fish that shark can go and eat that fish.
// */
//public class SharkCell{
//
//    public int[] move(List<int[]> positions, int cnt) {
//        var random = new Random();
//        int[] nextLoc = positions.get(random.nextInt(cnt)); //store movable positions for shark as int[]
//        return nextLoc;
//    }
//
//    public boolean dead(int energy, Grid g){ //When no more energy, shark will be die.
//        return (energy == 0) ? true: false;
//    }
//
//}