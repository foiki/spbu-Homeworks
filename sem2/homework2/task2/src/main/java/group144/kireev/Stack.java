package group144.kireev;

public interface Stack {
    void push (int number);
    int pop() throws NullPointerException;
    void printStack() throws NullPointerException;
    boolean isEmpty();
    int getSize();
}
