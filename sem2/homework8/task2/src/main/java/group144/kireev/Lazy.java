package group144.kireev;

/**
 * Interface describes Lazy computation
 * @param <T> - type of return value
 */
public interface Lazy<T> {

    /**
     * Method performs calculation of value
     * It will calculated only at first time. Another calls will return same object
     * @return T - calculated value
     */
    T get();
}