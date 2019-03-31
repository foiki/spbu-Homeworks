package group144.kireev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {
    @Test
    void pushAndPopTest() throws NoSuchElementInListException {
        LinkedList<Integer> list = new LinkedList<>();
        list.push(10);
        list.push(20);
        assertEquals(Integer.valueOf(10), list.pop(10));
        assertEquals(Integer.valueOf(20), list.pop(20));
        assertThrows(NoSuchElementInListException.class, () -> list.pop(10));
    }

    @Test
    void isEmptyTest() {
        LinkedList<String> list = new LinkedList<>();
        assertTrue(list.isEmpty());
        list.push("element");
        assertFalse(list.isEmpty());
    }

    @Test
    void getSizeTest() {
        LinkedList<String> list = new LinkedList<>();
        assertEquals(0, list.getSize());
        list.push("element");
        assertEquals(1, list.getSize());
    }
}