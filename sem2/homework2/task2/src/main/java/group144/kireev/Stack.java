package group144.kireev;

/** Interface realizing Stack */
public interface Stack {

    /** Method adds element to stack*/
    void push (int number);

    /** Method removes element from top of stack*/
    int pop() throws EmptyStackException;

    /** Override method prints stack */
    void printStack();

    /**  Method checks for stack emptiness */
    boolean isEmpty();

    /** Method returns stack size */
    int getSize();
}
