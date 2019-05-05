package group144.kireev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {
    @Test
    void pushAndPopTest() throws ElementNotFoundException {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(10);
        list.add(20);
        assertEquals(Integer.valueOf(10), list.pop(10));
        assertEquals(Integer.valueOf(20), list.pop(20));
        assertThrows(ElementNotFoundException.class, () -> list.pop(10));
    }

    @Test
    void isEmptyTest() {
        LinkedList<String> list = new LinkedList<>();
        assertTrue(list.isEmpty());
        list.add("element");
        assertFalse(list.isEmpty());
    }

    @Test
    void getSizeTest() {
        LinkedList<String> list = new LinkedList<>();
        assertEquals(0, list.getSize());
        list.add("element");
        assertEquals(1, list.getSize());
    }
}