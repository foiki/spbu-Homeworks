package group144.kireev;

import java.util.NoSuchElementException;

/**
 * A class that realizes single-linked list
 * @param <T> Type parameter
 */
public class LinkedList<T> {
    private ListElement<T> head;
    private int size;

    public boolean isEmpty() {

        return head == null;
    }

    /**
     * A method adding a new element to the list
     * @param value to add
     */
    public void push(T value) {
        head = new ListElement<>(value, head);
        ++size;
    }

    /** A method printing the list*/
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
    public T pop(T value) throws NoSuchElementInListException {
        if (head == null) {
            throw new NoSuchElementInListException("No such element in the list!");
        }
        if (head.value == value) {
            head = head.next;
            --size;
            return value;
        }
        ListElement<T> current = head;
        while (current.next != null && !current.next.value.equals(value)) {
            current = current.next;
        }
        if (current.next != null) {
            current.setNext(current.next.next);
            --size;
            return value;
        }
        throw new NoSuchElementInListException("No such element in the list!");
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

        private ListElement(T value) {
            this.value = value;
            this.next = null;
        }

        private void setNext(ListElement<T> next) {
            this.next = next;
        }
    }
}


