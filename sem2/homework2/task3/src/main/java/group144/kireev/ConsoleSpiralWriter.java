package group144.kireev;

import java.io.PrintStream;

import static group144.kireev.SpiralArrayToString.arrayToString;

/** Implements spiral printing to console*/
public class ConsoleSpiralWriter implements SpiralWriter {
    @Override
    /** The first way to implement a writing method is to print to a file*/
    public void printSpiral(int[][] array) throws WrongArrayException {
        arrayToString(array, new PrintStream(System.out));
    }
}
