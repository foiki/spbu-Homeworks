package group144.kireev;

/**
 *A class that realizes single-linked list
 */

public class List {
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

        private int getValue() {

            return value;
        }

        private ListElement getNext() {

            return next;
        }

        private void setNext(ListElement next) {
            this.next = next;
        }
    }

    private ListElement head;

    private boolean isEmpty() {
        return head != null;
    }

    /**
     * A method adding a new element to the list
     */

    public void add(int value) {
        if (!isEmpty()) {
            head = new ListElement(value);
        }
        else {
            if (head.getValue() > value) {
                head = new ListElement(value, head);
            }
            else {
                ListElement current = head;
                while (current.getNext() != null && current.next.getValue() < value) {
                    current = current.getNext();
                }
                ListElement newElement = new ListElement(value, current.getNext());
                current.setNext(newElement);
            }
        }
    }

    /**
     * A method printing the list
     */

    public void printList() {
        ListElement current = head;
        while (current != null) {
            System.out.println(current.getValue() + " ");
            current = current.getNext();
        }
    }

    /**
     * A method deleting element from the list
     */

    public void deleteElement(int value) {
        if (head == null) {
            System.out.println("List is empty!");
        }
        else {
            if (head.getValue() == value) {
                head = head.getNext();
            }
            else {
                ListElement current = head;
                while (current.getNext() != null && current.next.getValue() != value) {
                    current = current.getNext();
                }
                if (current.getNext() != null) {
                    current.setNext(current.getNext().getNext());
                }
                else {
                    System.out.println("Not found!");
                }
            }
        }

    }

    /**
     * A method checking whether an item belongs to a list
     */

    public void exist(int value) {
        if (head == null) {
            System.out.println("List is empty!");
        }
        else {
            ListElement current = head;
            while (current != null && current.getValue() != value) {
                current = current.getNext();
            }
            if (current != null) {
                System.out.println("This element belongs to the list");
            }
            else {
                System.out.println("This element does not belong to the list");
            }
        }
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

}
