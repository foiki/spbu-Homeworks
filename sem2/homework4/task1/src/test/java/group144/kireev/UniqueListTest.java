package group144.kireev;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UniqueListTest {
    @Test
    public void pushAndPopTest() throws NoSuchElementInListException {
        UniqueList<Integer> list = new UniqueList<>();
        list.push(10);
        list.push(20);
        assertEquals(10, list.pop(10));
        assertEquals(20, list.pop(20));
        assertThrows(NoSuchElementInListException.class, () -> list.pop(10));
    }

    @Test
    public void addingSimilarElements() throws ElementAlreadyAddedException {
        UniqueList<Integer> list = new UniqueList<>();
        list.push(10);
        assertThrows(ElementAlreadyAddedException.class, () -> list.push(10));
    }
}