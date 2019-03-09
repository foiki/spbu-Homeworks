package group144.kireev;


public class LinkedStack implements Stack {
    private StackElement top;
    private int length;

    @Override
    public void push(int value) {
        top = new StackElement(value, top);
        ++length;
    }

    @Override
    public int pop() throws NullPointerException {
        if (isEmpty()) {
            throw new NullPointerException("Nothing to delete!");
        }
        int removed = top.value;
        top = top.next;
        --length;
        return removed;
    }

    @Override
    public void printStack() throws NullPointerException {
        if (top == null) {
            throw new NullPointerException("No elements in the stack!");
        }
        StackElement current = top;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public int getSize() {
        return length;
    }

    private class StackElement {
        private int value;
        private StackElement next;

        public StackElement(int value, StackElement next) {
            this.value = value;
            this.next = next;
        }
    }
}
