package group144.kireev;

/** A class that realizes single-linked list */
public class List {
    private ListElement head;

    private boolean isEmpty() {

        return head != null;
    }

    /** A method adding a new element to the list */
    public void add(int value) {
        if (!isEmpty()) {
            head = new ListElement(value);
            return;
        }
        if (head.value > value) {
            head = new ListElement(value, head);
            return;
        }
        ListElement current = head;
        while (current.next != null && current.next.value < value) {
            current = current.next;
        }
        ListElement newElement = new ListElement(value, current.next);
        current.setNext(newElement);
    }

    /** A method printing the list */
    public void printList() {
        ListElement current = head;
        while (current != null) {
            System.out.println(current.value + " ");
            current = current.next;
        }
    }

    /** A method deleting element from the list */
    public void deleteElement(int value) {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }
        if (head.value == value) {
            head = head.next;
            System.out.println("Element " + value + " has been deleted");
            return;
        }
        ListElement current = head;
        while (current.next != null && current.next.value != value) {
            current = current.next;
        }
        if (current.next != null) {
            current.setNext(current.next.next);
            System.out.println("Element " + value + " has been deleted");
            return;
        }
        System.out.println("Not found!");
    }

    /** A method checking whether an item belongs to a list */
    public void exist(int value) {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }
        ListElement current = head;
        while (current != null && current.value != value) {
            current = current.next;
        }
        if (current == null) {
            System.out.println("This element does not belong to the list");
            return;
        }
        System.out.println("This element belongs to the list");
    }

    public static void main(String[] args) {
        List list = new List();
        list.add(4);
        list.add(3);
        list.add(5);
        list.add(2);
        list.add(1);
        list.printList();
        list.deleteElement(4);
        list.deleteElement(4);
        list.add(6);
        list.deleteElement(1);
        list.printList();
        list.exist(6);
        list.exist(4);
    }

    private class ListElement {
        private int value;
        private ListElement next;

        private ListElement(int value, ListElement next) {
            this.value = value;
            this.next = next;
        }

        private ListElement(int value) {
            this.value = value;
            this.next = null;
        }

        private void setNext(ListElement next) {

            this.next = next;
        }
    }
}
