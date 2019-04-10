package group144.kireev;

/** Implements simple QuickSort**/
public class QuickSort implements Sorter {
    @Override
    public void sort(int[] array) {
        if (array.length != 0) {
            sort(array, 0, array.length - 1);
        }
    }

    public void sort(int[] array, int begin, int end) {
        int left = begin;
        int right = end;
        int mid = array[(left + right) / 2];
        while (left <= right) {
            while (array[left] < mid) {
                ++left;
            }
            while (array[right] > mid) {
                --right;
            }
            if (left <= right) {
                int c = array[left];
                array[left] = array[right];
                array[right] = c;
                ++left;
                --right;
            }
            if (left < end) {
                sort(array, left, end);
            }
            if (right > begin) {
                sort(array, begin, right);
            }
        }
    }
}
