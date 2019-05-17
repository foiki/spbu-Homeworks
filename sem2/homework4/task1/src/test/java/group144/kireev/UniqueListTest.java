package group144.kireev;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UniqueListTest {
    @Test
    void pushAndPopTest() throws ElementNotFoundException {
        UniqueList<Integer> list = new UniqueList<>();
        list.add(10);
        list.add(20);
        assertEquals(Integer.valueOf(10), list.pop(10));
        assertEquals(Integer.valueOf(20), list.pop(20));
        assertThrows(ElementNotFoundException.class, () -> list.pop(10));
    }

    @Test
    void addingSimilarElements() throws ElementAlreadyAddedException {
        UniqueList<Integer> list = new UniqueList<>();
        list.add(10);
        assertThrows(ElementAlreadyAddedException.class, () -> list.add(10));
    }
}