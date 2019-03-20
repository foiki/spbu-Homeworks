package group144.kireev;

public interface Stack {
    void push (int number);
    int pop() throws EmptyStackException;
    void printStack();
    boolean isEmpty();
    int getSize();
}
