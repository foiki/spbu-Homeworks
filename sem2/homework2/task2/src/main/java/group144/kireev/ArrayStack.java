package group144.kireev;

/** Implements ArrayStack**/
public class ArrayStack implements Stack {
    private int length = 0;
    private int maxLength = 100;
    private int[] array = new int[maxLength];

    /** Override a method that adds element to stack*/
    @Override
    public void push(int value) {
        array[length] = value;
        ++length;
    }

    /** Override a method that removes element from top of stack*/
    @Override
    public int pop() throws NullPointerException {
        if (isEmpty()) {
            throw new NullPointerException("Nothing to delete!");
        }
        return array[--length];
    }

    /** Override a method that removes element from top of stack*/
    @Override
    public void printStack() throws NullPointerException {
        if (isEmpty()) {
            throw new NullPointerException("Nothing to delete!");
        }
        for (int i = 0; i < length; ++i) {
            System.out.print(array[i] + " ");
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
}
