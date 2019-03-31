package group144.kireev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniqueListTest {
    @Test
    void pushAndPopTest() throws NoSuchElementInListException {
        UniqueList<Integer> list = new UniqueList<>();
        list.push(10);
        list.push(20);
        assertEquals(Integer.valueOf(10), list.pop(10));
        assertEquals(Integer.valueOf(20), list.pop(20));
        assertThrows(NoSuchElementInListException.class, () -> list.pop(10));
    }

    @Test
    void addingSimilarElements() throws ElementAlreadyAddedException {
        UniqueList<Integer> list = new UniqueList<>();
        list.push(10);
        assertThrows(ElementAlreadyAddedException.class, () -> list.push(10));
    }
}