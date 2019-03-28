package group144.kireev;

/**
 * Class realizing Simple Linked List without repetitive elements
 * @param <T> Type parameter
 */
public class UniqueList<T> extends LinkedList<T> {

    /**
     * Method adds]ing element to list
     * @param value adding value
     * @throws ElementAlreadyAddedException if element is already added
     */
    @Override
    public void push(T value) throws ElementAlreadyAddedException {
        if (!exist(value)) {
            super.push(value);
            return;
        }
        throw new ElementAlreadyAddedException();
    }
}
