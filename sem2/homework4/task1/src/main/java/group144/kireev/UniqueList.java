package group144.kireev;

/**
 * Class realizes Simple Linked List without repetitive elements
 * @param <T> Type parameter
 */
public class UniqueList<T> extends LinkedList<T> {

    /**
     * Method adding element to list, collect only unique elements
     * @param value adding value
     * @throws ElementAlreadyAddedException if element is already added
     */
    @Override
    public void add(T value) throws ElementAlreadyAddedException {
        if (!exist(value)) {
            super.add(value);
            return;
        }
        throw new ElementAlreadyAddedException();
    }
}
