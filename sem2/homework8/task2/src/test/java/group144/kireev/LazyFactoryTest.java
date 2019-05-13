package group144.kireev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LazyFactoryTest {
    @Test
    void oneThreadSimpleSupplierTest() {
        Lazy<Integer> lazy = LazyFactory.createOneThreadLazy(() -> 5);
        assertEquals(5, (int)lazy.get());
        lazy.get();
        assertEquals(5, (int)lazy.get());
    }

    @Test
    void oneThreadNullSupplierTest() {
        Lazy<Integer> lazy = LazyFactory.createOneThreadLazy(() -> null);
        assertNull(lazy.get());
        lazy.get();
        lazy.get();
        assertNull(lazy.get());
    }

    @Test
    void multiThreadCommonSupplierTest() {
        Lazy<String> lazy = LazyFactory.createMultiTreadLazy(() -> "Hello");
        lazy.get();
        assertEquals("Hello", lazy.get());
        assertEquals("Hello", lazy.get());
    }

    @Test
    void multiThreadNullSupplierTest() {
        Lazy<Integer> lazy = LazyFactory.createMultiTreadLazy(() -> null);
        assertNull(lazy.get());
        lazy.get();
        lazy.get();
        assertNull(lazy.get());
        assertNull(lazy.get());
    }

    @Test
    void multiThreadRaceTest() throws InterruptedException {
        int[] counter = new int[1];
        Lazy<Integer> lazy = LazyFactory.createMultiTreadLazy(() -> {
            ++counter[0];
            return 24;
        });
        int numberOfThreads = 1000;
        Thread[] threads = new Thread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; ++i) {
            threads[i] = new Thread(() -> assertEquals(24, (int)lazy.get()));
        }
        for (int i = 0; i < numberOfThreads; ++i) {
            threads[i].start();
        }
        for (int i = 0; i < numberOfThreads; ++i) {
            threads[i].join();
        }
        assertEquals(1, counter[0]);
        assertEquals(24, (int)lazy.get());
    }
}