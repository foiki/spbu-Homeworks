package group144.kireev;

import java.io.PrintWriter;

import static group144.kireev.SpiralArrayToString.arrayToString;

public class FileSpiralWriter implements SpiralWriter {
    @Override
    public void printSpiral(int[][] array) throws Exception {
        String answer = arrayToString(array);
        PrintWriter out = new PrintWriter("output.txt");
        out.println(answer);
        out.close();
    }
}
