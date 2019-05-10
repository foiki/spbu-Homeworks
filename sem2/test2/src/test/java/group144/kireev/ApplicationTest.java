package group144.kireev;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import static group144.kireev.Application.runApplication;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {
    @Test
    void runApplicationTest() {
        LinkedList<String> firstList = new LinkedList<>();
        firstList.add("1");
        LinkedList<String> secondList = new LinkedList<>();
        secondList.add("4");
        secondList.add("5");
        secondList.add("6");
        secondList.add("7");
        LinkedList<String> thirdList = new LinkedList<>();
        thirdList.add("2");
        thirdList.add("3");
        LinkedList<LinkedList<String>> list = new LinkedList<>();
        list.add(firstList);
        list.add(secondList);
        list.add(thirdList);
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(arrayOutputStream));
        runApplication(list);
        assertEquals("1 2 3 4 5 6 7 ", arrayOutputStream.toString());
    }
}