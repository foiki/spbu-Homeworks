package group144.kireev;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static group144.kireev.Application.runApplication;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {
    @Test
    void runApplicationTest() {
        String[] elements = {"1", "4 5 6 7", "2 3"};
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(arrayOutputStream));
        runApplication(elements);
        assertEquals("1 2 3 4 5 6 7 ", arrayOutputStream.toString());
    }
}