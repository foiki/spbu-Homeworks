package group144.kireev;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;

import static group144.kireev.SpiralArrayToString.arrayToString;

/** Implements spiral printing to file*/
public class FileSpiralWriter implements SpiralWriter {
    @Override
    /** The first way to implement a writing method is to print to a file*/
    public void printSpiral(int[][] array) throws FileNotFoundException, WrongArrayException {
        arrayToString(array, new PrintStream("output.txt"));
    }
}
