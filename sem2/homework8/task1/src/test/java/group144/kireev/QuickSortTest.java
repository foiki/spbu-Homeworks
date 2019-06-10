package group144.kireev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {
    private QuickSort sorter = new QuickSort();

    @Test
    void oneElementArrayTest() {
        int[] array = {1};
        int[] sortedArray = {1};
        sorter.sort(array);
        assertArrayEquals(array, sortedArray);
    }

    @Test
    void emptyArrayTest() {
        int[] array = {};
        int[] sortedArray = {};
        sorter.sort(array);
        assertArrayEquals(array, sortedArray);
    }

    @Test
    void simpleSortTest() {
        int[] array = {5, 4, 1, 10, 7};
        int[] sortedArray = {1, 4, 5, 7, 10};
        sorter.sort(array);
        assertArrayEquals(array, sortedArray);
    }

    @Test
    void sortTest() {
        int[] array = {100, -200, 14, 10, -2, 29, 19, -10, 340, 15, 10, -340};
        int[] sortedArray = {-340, -200, -10, -2, 10, 10, 14, 15, 19, 29, 100, 340};
        sorter.sort(array);
        assertArrayEquals(array, sortedArray);
    }
}