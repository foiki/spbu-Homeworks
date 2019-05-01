package group144.kireev;

/** Interface realizing Stack */
public interface Stack {
    void push (int number);
    int pop() throws EmptyStackException;
    void printStack();
    boolean isEmpty();
    int getSize();
}
