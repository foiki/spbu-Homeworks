package group144.kireev;

import java.util.function.Supplier;

public class OneThreadLazy<T> implements Lazy {
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

    OneThreadLazy (Supplier<T> supplier) {
        
    }
}
