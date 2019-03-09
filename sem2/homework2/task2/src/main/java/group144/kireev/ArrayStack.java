package group144.kireev;

public class ArrayStack implements Stack {
    private int length = 0;
    private int maxLength = 100;
    private int[] array = new int[maxLength];

    @Override
    public void push(int value) {
        array[length] = value;
        ++length;
    }

    @Override
    public int pop() throws NullPointerException {
        if (isEmpty()) {
            throw new NullPointerException("Nothing to delete!");
        }
        return array[--length];
    }

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

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public int getSize() {
        return length;
    }
}
