package group144.kireev;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/** Simple QuickSorter uses multi-tread algorithm */
public class QuickSortTreads implements Sorter {
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
        ForkJoinPool.commonPool().invoke(new ForkJoinSort(0, array.length - 1));
    }

    /** Class realizes multi-thread QuickSort */
    private class ForkJoinSort extends RecursiveAction {
        int begin;
        int end;

        private ForkJoinSort(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (begin >= end) {
                return;
            }
            int left = begin;
            int right = end;
            int mid = array[left + (right - left) / 2];
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
            }
            invokeAll(new ForkJoinSort(begin, left - 1), new ForkJoinSort(left, end));
        }
    }
}
