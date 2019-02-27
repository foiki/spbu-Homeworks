package group144.kireev;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuickSortTests {
    private static Sorter sorter = new QuickSort();

    @Test
    public void sortEmpty() {
        int[] array = {};
        int[] sorted = {};
        sorter.sort(array);
        assertArrayEquals(array, sorted);
    }

    @Test
    public void sortReversed() {
        int[] array = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] sorted = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        sorter.sort(array);
        assertArrayEquals(array, sorted);
    }

    @Test
    public void sortUsual() {
        int[] array = {1, 5, 4, 3, 6, 9, 0, 1, 5, 8, 11, 12, 100, -2, -10};
        int[] sorted = {-10, -2, 0, 1, 1, 3, 4, 5, 5, 6, 8, 9, 11, 12, 100};
        sorter.sort(array);
        assertArrayEquals(array, sorted);
    }

    @Test
    public void sortOneElement() {
        int[] array = {1};
        int[] sorted = {1};
        sorter.sort(array);
        assertArrayEquals(array, sorted);
    }
}
