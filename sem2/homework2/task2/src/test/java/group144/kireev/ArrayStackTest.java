package group144.kireev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStackTest {

    @Test
    void pushAndPopTest() {
        Stack stack = new ArrayStack();
        stack.push(10);
        stack.push(20);
        assertEquals(stack.pop(), 20);
        assertEquals(stack.pop(), 10);
        try {
            stack.pop();
        } catch (NullPointerException e) {
            assertEquals("Nothing to delete!", e.getMessage());
        }
    }

    @Test
    void isEmptyTest() {
        Stack stack = new ArrayStack();
        assertEquals(true, stack.isEmpty());
        stack.push(1);
        assertEquals(false, stack.isEmpty());
    }

    @Test
    void getSize() {
        Stack stack = new ArrayStack();
        assertEquals(0, stack.getSize());
        stack.push(1);
        assertEquals(1, stack.getSize());
    }
}