package group144.kireev;

/**
 * Class realizing priority queue
 * @param <T> Type of the Value stored in queue
 */
public class PriorityQueue<T> {
    private QueueElement<T> head = null;
    private int size;

    /** Returns if queue is empty */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Adding value with given priority to the queue
     * @param value element to put
     * @param priority priority of the element
     */
    public void enqueue(T value, int priority) {
        ++size;
        if (isEmpty() || head.priority < priority) {
            head = new QueueElement<>(value, priority, head);
            return;
        }
        QueueElement<T> current = head;
        while(current.next != null && current.next.priority > priority) {
            current = current.next;
        }
        current.next = new QueueElement<>(value, priority, current.next);
    }

    /**
     * Removing element with maximum priority from the queue
     * @return Value of element with highest priority
     * @throws EmptyQueueException if nothing to return(Queue is empty)
     */
    public T dequeue() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        --size;
        T toReturn = head.value;
        head = head.next;
        return toReturn;
    }

    /**
     * Class realizing queue element
     * @param <T> Type of the Value stored in QueueElement
     */
    private class QueueElement<T> {
        private T value;
        private int priority;
        private QueueElement<T> next;

        /**
         * Constructor for QueueElement
         * @param value stores value of this element.
         * @param  priority stores priority(type int) of this element.
         * @param  next stores address of the next element.
         */
        QueueElement(T value, int priority, QueueElement<T> next) {
            this.value = value;
            this.priority = priority;
            this.next = next;
        }
    }
}
