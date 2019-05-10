package group144.kireev;

import java.util.LinkedList;

/** Class realizes SortedSet collects LinkedLists of Strings */
public class SortedSet implements ListsComparator {
    private Node head;

    /**
     * @return if SortedSet is empty
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * @param element to add to SortedSet in increasing order
     */
    public void add(LinkedList<String> element) {
        if (isEmpty()) {
            head = new Node(element);
            return;
        }
        if (compare(head.value, element) > 0) {
            head = new Node(element, head);
            return;
        }
        Node current = head;
        while (current.next != null && compare(current.next.value, element) < 0) {
            current = current.next;
        }
        current.next = new Node(element, current.next);
    }

    /**
     * Method comparing two elements
     * @param first element to compare
     * @param second element to compare
     * @return negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second
     */
    @Override
    public int compare(LinkedList<String> first, LinkedList<String> second) {
        return first.size() - second.size();
    }

    /** Method prints SortedSet to console */
    public void printSet() {
        Node current = head;
        while (current != null) {
            current.print();
            current = current.next;
        }
    }

    /** Class realizes Node of SortedSet */
    private class Node {
        private LinkedList<String> value;
        private Node next;

        private Node(LinkedList<String> value, Node next) {
            this.value = value;
            this.next = next;
        }

        private Node(LinkedList<String> value) {
            this.value = value;
            this.next = null;
        }

        /** Method prints Node to console */
        private void print() {
            for (String string : value) {
                System.out.print(string + " ");
            }
        }
    }
}
