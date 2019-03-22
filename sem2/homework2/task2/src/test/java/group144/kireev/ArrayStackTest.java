package group144.kireev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStackTest {

    @Test
    void pushAndPopTest() throws EmptyStackException {
        Stack stack = new ArrayStack();
        stack.push(10);
        stack.push(20);
        assertEquals(stack.pop(), 20);
        assertEquals(stack.pop(), 10);
        assertThrows(EmptyStackException.class, stack::pop);
    }

    @Test
    void isEmptyTest() {
        Stack stack = new ArrayStack();
        assertTrue(stack.isEmpty());
        stack.push(1);
        assertFalse(stack.isEmpty());
    }

    @Test
    void getSize() {
        Stack stack = new ArrayStack();
        assertEquals(0, stack.getSize());
        stack.push(1);
        assertEquals(1, stack.getSize());
    }
}