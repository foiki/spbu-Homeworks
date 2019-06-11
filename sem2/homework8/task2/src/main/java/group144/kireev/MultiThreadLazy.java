package group144.kireev;

import java.util.function.Supplier;

public class MultiThreadLazy<T> implements Lazy<T> {

    private volatile T value = null;
    private volatile Supplier<T> supplier;

    @Override
    public T get() {
        if (supplier != null) {
            synchronized (this) {
                if (supplier != null) {
                    value = supplier.get();
                    supplier = null;
                }
            }
        }
        return value;
    }

    public MultiThreadLazy(Supplier<T> supplier) {
        this.supplier = supplier;
    }
}
