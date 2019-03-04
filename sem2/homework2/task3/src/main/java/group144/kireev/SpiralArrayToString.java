package group144.kireev;

public class SpiralArrayToString {
    public static String arrayToString(int[][] array) throws Exception {
        if (array == null || array.length % 2 == 0) {
            throw new Exception("Wrong array length!");
        }
        String result = "";
        int currentLine = array.length / 2;
        int currentColumn = array.length / 2;
        result += array[currentLine][currentColumn] + " ";
        --currentColumn;
        int currentSideSize = 1;
        while ((currentLine != 0 || currentColumn != 0) && currentSideSize != array.length)
        {
            currentSideSize += 2;
            for (int i = 1; i < currentSideSize; ++i)
            {
                result += array[currentLine][currentColumn] + " ";
                ++currentLine;
            }
            --currentLine;
            ++currentColumn;
            for (int i = 0; i < currentSideSize - 1; ++i)
            {
                result += array[currentLine][currentColumn] + " ";
                ++currentColumn;
            }
            --currentColumn;
            --currentLine;
            for (int i = 1; i < currentSideSize; ++i)
            {
                result += array[currentLine][currentColumn] + " ";
                --currentLine;
            }
            ++currentLine;
            --currentColumn;
            for (int i = 0; i < currentSideSize - 1; ++i)
            {
                result += array[currentLine][currentColumn] + " ";
                --currentColumn;
            }
        }
        return result;
    }
}
