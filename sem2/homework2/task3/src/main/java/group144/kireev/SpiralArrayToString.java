package group144.kireev;

import java.io.PrintStream;

/** Class to translate of the array by a spiral into a string */
public class SpiralArrayToString {
    public static void arrayToString(int[][] array, PrintStream stream) throws WrongArrayException {
        /** The method implements the translation of the array by a spiral into a string */
        if (array == null || array.length % 2 == 0) {
            throw new WrongArrayException("Wrong array length!");
        }
        int currentLine = array.length / 2;
        int currentColumn = array.length / 2;
        stream.print(array[currentLine][currentColumn] + " ");
        --currentColumn;
        int currentSideSize = 1;
        while ((currentLine != 0 || currentColumn != 0) && currentSideSize != array.length)
        {
            currentSideSize += 2;
            for (int i = 1; i < currentSideSize; ++i)
            {
                stream.print(array[currentLine][currentColumn] + " ");
                ++currentLine;
            }
            --currentLine;
            ++currentColumn;
            for (int i = 0; i < currentSideSize - 1; ++i)
            {
                stream.print(array[currentLine][currentColumn] + " ");
                ++currentColumn;
            }
            --currentColumn;
            --currentLine;
            for (int i = 1; i < currentSideSize; ++i)
            {
                stream.print(array[currentLine][currentColumn] + " ");
                --currentLine;
            }
            ++currentLine;
            --currentColumn;
            for (int i = 0; i < currentSideSize - 1; ++i)
            {
                stream.print(array[currentLine][currentColumn] + " ");
                --currentColumn;
            }
        }
    }
}
