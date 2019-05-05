package group144.kireev;

/** Class represents an exception that should be raised when user tries to remove missing element */
public class ElementNotFoundException extends Exception {
    ElementNotFoundException(String message) {
        super(message);
    }
}
