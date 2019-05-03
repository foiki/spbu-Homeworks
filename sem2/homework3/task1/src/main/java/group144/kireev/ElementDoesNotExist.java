package group144.kireev;

/** Class describes an Exception thrown if element does not exist to HashTable */
public class ElementDoesNotExist extends Exception {
    ElementDoesNotExist(String message) {
        super(message);
    }
}