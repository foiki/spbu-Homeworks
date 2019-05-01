package group144.kireev;

/** Implements ArrayStack*/
public class ArrayStack implements Stack {
    private int length = 0;
    private int maxLength = 100;
    private int[] array = new int[maxLength];

    /** Override a method that adds element to stack*/
    @Override
    public void push(int value) {
        array[length] = value;
        ++length;
        if (maxLength == length) {
            increaseStackSize();
        }
    }

    /** Increasing size of Stack */
    private void increaseStackSize() {
        int[] newArray = new int[maxLength * 2];
        System.arraycopy(array, 0, newArray, 0, length);
        maxLength *= 2;
        array = newArray;
    }

    /** Override a method that removes element from top of stack*/
    @Override
    public int pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException("Nothing to delete!");
        }
        if (length < maxLength / 2) {
            decreaseStackSize();
        }
        return array[--length];
    }

    /** Decreasing size of Stack */
    private void decreaseStackSize() {
        maxLength = length + 5;
        int[] newArray = new int[maxLength];
        System.arraycopy(array, 0, newArray, 0, length);
        array = newArray;
    }

    /** Override a method that removes element from top of stack*/
    @Override
    public void printStack() {
        if (isEmpty()) {
            System.out.println("No elements in the stack!");
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
