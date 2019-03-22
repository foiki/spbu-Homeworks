package group144.kireev;

import java.io.FileNotFoundException;

/** Interface of SpiralWriter with single method*/
public interface SpiralWriter {
    /** Printing method which can throw exception if the array is incorrect*/
    void printSpiral(int[][] array) throws FileNotFoundException, WrongArrayException;
}
