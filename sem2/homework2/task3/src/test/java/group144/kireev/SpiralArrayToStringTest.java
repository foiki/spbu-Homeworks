package group144.kireev;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static group144.kireev.SpiralArrayToString.arrayToString;
import static org.junit.jupiter.api.Assertions.*;

class SpiralArrayToStringTest {

    @Test
    void size3Array() throws WrongArrayException {
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(arrayOutputStream));
        int[][] array = {{9, 8, 7}, {2, 1, 6}, {3, 4, 5}};
        arrayToString(array, System.out);
        assertEquals("1 2 3 4 5 6 7 8 9 ", arrayOutputStream.toString());
    }

    @Test
    void emptyArray() {
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(arrayOutputStream));
        assertThrows(WrongArrayException.class, () -> arrayToString(null, System.out));
    }

    @Test
    void wrongLength() {
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(arrayOutputStream));
        int[][] array = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        assertThrows(WrongArrayException.class, () -> arrayToString(array, System.out));
    }
}