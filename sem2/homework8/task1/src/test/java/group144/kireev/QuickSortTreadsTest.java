package group144.kireev;

import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class QuickSortTreadsTest {
    private QuickSortTreads sorter = new QuickSortTreads();

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

    @Test
    void longArrayTimeTest() {
        QuickSort simpleSorter = new QuickSort();
        int arrayLength = 1000;
        int[] arrayFirst = new int[arrayLength];
        int[] arraySecond = new int[arrayLength];
        Random random = new Random();
        for (int i = 0; i < arrayLength; ++i) {
            int newNumber = random.nextInt();
            arrayFirst[i] = newNumber;
            arraySecond[i] = newNumber;
        }
        long startFirst = System.currentTimeMillis();
        sorter.sort(arrayFirst);
        long multiThreadTime = System.currentTimeMillis() - startFirst;
        long startSecond = System.currentTimeMillis();
        simpleSorter.sort(arraySecond);
        long oneThreadTime = System.currentTimeMillis() - startSecond;
        assertTrue(oneThreadTime > multiThreadTime);
    }
}