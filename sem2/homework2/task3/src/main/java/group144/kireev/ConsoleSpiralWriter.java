package group144.kireev;

import static group144.kireev.SpiralArrayToString.arrayToString;

public class ConsoleSpiralWriter implements SpiralWriter {
    @Override
    public void printSpiral(int[][] array) throws Exception {
        System.out.println(arrayToString(array));
    }
}
