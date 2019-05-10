package group144.kireev;

/** Simple QuickSorter uses one tread algorithm */
public class QuickSort implements Sorter {
    private int[] array;

    /**
     * Method sorts an array
     * @param array to sort
     */
    @Override
    public void sort(int[] array) {
        this.array = array;
        if (array.length <= 1) {
            return;
        }
        quickSort(0, array.length - 1);
    }

    /**
     * Method performs QuickSort to an array
     * @param begin index of first element
     * @param end index of last element
     */
    private void quickSort(int begin, int end) {
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
                quickSort(left, end);
            }
            if (right > begin) {
                quickSort(begin, right);
            }
        }
    }
}
