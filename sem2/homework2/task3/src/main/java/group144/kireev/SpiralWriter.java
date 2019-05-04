package group144.kireev;

import java.io.FileNotFoundException;

/** Interface describes a SpiralWriter */
public interface SpiralWriter {
    /** Printing method which can throw exception if the array is incorrect */
    void printSpiral(int[][] array) throws FileNotFoundException, WrongArrayException;
}
