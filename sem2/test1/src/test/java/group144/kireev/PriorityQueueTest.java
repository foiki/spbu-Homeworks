package group144.kireev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriorityQueueTest {

    @Test
    void isEmptyTest() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        assertTrue(queue.isEmpty());
        queue.enqueue(10, 10);
        assertFalse(queue.isEmpty());
    }

    @Test
    void enqueueAndDequeueTest() throws EmptyQueueException {
        PriorityQueue<String> queue = new PriorityQueue<>();
        queue.enqueue("Vasya", 10);
        queue.enqueue("Petya", 20);
        assertEquals("Petya", queue.dequeue());
    }

    @Test
    void emptyQueueExceptionTest() throws EmptyQueueException {
        PriorityQueue<String> queue = new PriorityQueue<>();
        queue.enqueue("Vasya", 10);
        queue.dequeue();
        assertThrows(EmptyQueueException.class, queue::dequeue);
    }
}