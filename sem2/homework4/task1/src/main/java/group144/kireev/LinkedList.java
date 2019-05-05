package group144.kireev;

/**
 * Class realizes single-linked list
 * @param <T> Type parameter
 */
public class LinkedList<T> {
    private ListElement<T> head;
    private int size;

    /**
     * @return if the list is empty
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Method adding a new element to the list
     * @param value to add
     */
    public void add(T value) {
        head = new ListElement<>(value, head);
        ++size;
    }

    /** Method prints the list*/
    public void printList() {
        ListElement<T> current = head;
        while (current != null) {
            System.out.println(current.value + " ");
            current = current.next;
        }
    }

    /**
     * A method deleting element from the list
     * @param value to remove
     */
    public T pop(T value) throws ElementNotFoundException {
        ListElement<T> current = head;
        ListElement<T> previous = head;
        while (current != null && !current.value.equals(value)) {
            previous = current;
            current = current.next;
        }
        if (current != null) {
            previous.next = current.next;
            --size;
            return value;
        }
        throw new ElementNotFoundException("No such element in the list!");
    }

    /**
     * A method checking whether an item belongs to a list
     * @param value to check
     */
    public boolean exist(T value) {
        ListElement<T> current = head;
        while (current != null) {
            if (current.value.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    /**
     * Class realizing list element
     * @param <T> type parameter
     */
    private class ListElement<T> {
        private T value;
        private ListElement<T> next;

        private ListElement(T value, ListElement<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}


