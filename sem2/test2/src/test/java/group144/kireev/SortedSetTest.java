package group144.kireev;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class SortedSetTest {
    @Test
    void isEmptyTest() {
        SortedSet set = new SortedSet();
        assertTrue(set.isEmpty());
        LinkedList<String> firstList = new LinkedList<>();
        firstList.add("1");
        set.add(firstList);
        assertFalse(set.isEmpty());
    }

    @Test
    void compareTest() {
        SortedSet set = new SortedSet();
        LinkedList<String> firstList = new LinkedList<>();
        firstList.add("1");
        LinkedList<String> secondList = new LinkedList<>();
        secondList.add("4");
        secondList.add("5");
        secondList.add("6");
        secondList.add("7");
        assertEquals(1, set.compare(secondList, firstList));
        LinkedList<String> emptyList = new LinkedList<>();
        assertEquals(-1, set.compare(emptyList, firstList));
    }

    @Test
    void addTest() {
        SortedSet set = new SortedSet();
        LinkedList<String> firstList = new LinkedList<>();
        firstList.add("1");
        LinkedList<String> secondList = new LinkedList<>();
        secondList.add("4");
        secondList.add("5");
        secondList.add("6");
        secondList.add("7");
        set.add(secondList);
        set.add(firstList);
        LinkedList<String> thirdList = new LinkedList<>();
        thirdList.add("2");
        thirdList.add("3");
        set.add(thirdList);
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(arrayOutputStream));
        set.printSet();
        assertEquals("1 2 3 4 5 6 7 ", arrayOutputStream.toString());
    }


}