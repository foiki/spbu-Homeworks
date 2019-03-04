package group144.kireev;

import org.junit.jupiter.api.Test;

import static group144.kireev.SpiralArrayToString.arrayToString;
import static org.junit.jupiter.api.Assertions.*;

public class SpiralArrayToStringTest {

    @Test
    public void size3Array() {
        int[][] array = {{9, 8, 7}, {2, 1, 6}, {3, 4, 5}};
        String answer;
        try {
            answer = arrayToString(array);
        } catch (Exception e) {
            answer = e.getMessage();
        }
        assertEquals("1 2 3 4 5 6 7 8 9 ", answer);
    }

    @Test
    public void emptyArray() {
        int[][] array = null;
        String answer;
        try {
            answer = arrayToString(array);
        } catch (Exception e) {
            answer = e.getMessage();
        }
        assertEquals("Wrong array length!", answer);
    }

    @Test
    public void wrongLength() {
        int[][] array = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        String answer;
        try {
            answer = arrayToString(array);
        } catch (Exception e) {
            answer = e.getMessage();
        }
        assertEquals("Wrong array length!", answer);
    }
}