package group144.kireev;

import java.util.function.Supplier;

/** Class creates Lazy objects */
public class LazyFactory {

    /**
     * Method performs Lazy calculation using one thread
     * @param supplier to get answer
     * @param <T> - type of answer value
     * @return calculated value
     */
    public static <T> Lazy<T> createOneThreadLazy(Supplier<T> supplier) {
        return new OneThreadLazy<>(supplier);

    }

    /**
     * Method performs Lazy calculation using multi thread
     * @param supplier to get answer
     * @param <T> - type of answer value
     * @return calculated value
     */
    public static <T> Lazy<T> createMultiTreadLazy(Supplier<T> supplier) {
        return new MultiThreadLazy<>(supplier);
    }
}