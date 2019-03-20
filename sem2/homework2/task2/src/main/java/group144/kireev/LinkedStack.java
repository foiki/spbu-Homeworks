package group144.kireev;

/** Implements LinkedStack*/
public class LinkedStack implements Stack {
    private StackElement top;
    private int length;

    /** Override a method that adds element to stack*/
    @Override
    public void push(int value) {
        top = new StackElement(value, top);
        ++length;
    }

    /** Override a method that removes element from top of stack*/
    @Override
    public int pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException("Nothing to delete!");
        }
        int removed = top.value;
        top = top.next;
        --length;
        return removed;
    }

    /** Override a method that prints stack*/
    @Override
    public void printStack() {
        if (top == null) {
            System.out.println("No elements in the stack!");
        }
        StackElement current = top;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }

    /** Override a method that checks for stack emptiness*/
    @Override
    public boolean isEmpty() {

        return length == 0;
    }

    /** Override a method that returns stack size*/
    @Override
    public int getSize() {

        return length;
    }

    /** Implements StackElement**/
    private class StackElement {
        private int value;
        private StackElement next;

        public StackElement(int value, StackElement next) {
            this.value = value;
            this.next = next;
        }
    }
}
