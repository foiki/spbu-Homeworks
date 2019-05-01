package group144.kireev;

/** Class describes an Exception thrown when user tries to pop from empty stack */
public class EmptyStackException extends Exception {
    EmptyStackException(String message) {
        super(message);
    }
}
