package group144.kireev;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {
    @Test
    public void pushAndPopTest() throws NoSuchElementInListException {
        LinkedList<Integer> list = new LinkedList<>();
        list.push(10);
        list.push(20);
        assertEquals(10, list.pop(10));
        assertEquals(20, list.pop(20));
        assertThrows(NoSuchElementInListException.class, () -> list.pop(10));
    }

    @Test
    public void isEmptyTest() {
        LinkedList<String> list = new LinkedList<>();
        assertTrue(list.isEmpty());
        list.push("element");
        assertFalse(list.isEmpty());
    }

    @Test
    public void getSizeTest() {
        LinkedList<String> list = new LinkedList<>();
        assertEquals(0, list.getSize());
        list.push("element");
        assertEquals(1, list.getSize());
    }
}