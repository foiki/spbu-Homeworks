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
        return new Lazy<>() {
            private T value;
            private boolean wasCalculated = false;

            @Override
            public T get() {
                if (wasCalculated) {
                    return value;
                }
                value = supplier.get();
                wasCalculated = true;
                return value;
            }
        };
    }

    /**
     * Method performs Lazy calculation using one thread
     * @param supplier to get answer
     * @param <T> - type of answer value
     * @return calculated value
     */
    public static <T> Lazy<T> createMultiTreadLazy(Supplier<T> supplier) {
        return new Lazy<>() {
            private volatile T value = null;
            private volatile boolean wasCalculated = false;

            @Override
            public T get() {
                if (wasCalculated) {
                    return value;
                }
                synchronized (this) {
                    if (!wasCalculated) {
                        value = supplier.get();
                        wasCalculated = true;
                    }
                }
                return value;
            }
        };
    }
}
