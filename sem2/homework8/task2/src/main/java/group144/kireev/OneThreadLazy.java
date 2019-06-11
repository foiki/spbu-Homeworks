package group144.kireev;

import java.util.function.Supplier;

public class OneThreadLazy<T> implements Lazy<T> {
    private T value = null;
    private Supplier<T> supplier;

    @Override
    public T get() {
        if (supplier != null) {
            value = supplier.get();
            supplier = null;
        }
        return value;
    }

    public OneThreadLazy(Supplier<T> supplier) {
        this.supplier = supplier;
    }
}