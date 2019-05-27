/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

/**
 * A class for sorting an array of {@code ints} in parallel.
 * A {@code ForkJoinPool} is used for the parallelism, using the merge sort
 * algorithm the array is split into halves and a new sub task is created
 * for each part. Each sub task is dispatched to the {@code ForkJoinPool}
 * which will schedule the task to a {@code Thread}.
 * This happens until the size of the array is at most 2
 * elements long. At this point the array is sorted using a simple compare
 * and possibly a swap. The tasks then finish by using insert sort to
 * merge the two just sorted arrays.
 *
 * The idea of this class is to demonstrate the usage of RecursiveAction not
 * to implement the best possible parallel merge sort. This version creates
 * a small array for each merge (creating a lot of objects), this could
 * be avoided by keeping a single array.
 */
public class MergeSort {
    private final ForkJoinPool pool;

    private static class MergeSortTask extends RecursiveAction {
        private final int[] array;
        private final int low;
        private final int high;
        private static final int THRESHOLD = 8;

        /**
         * Creates a {@code MergeSortTask} containing the array and the bounds of the array
         *
         * @param array the array to sort
         * @param low the lower element to start sorting at
         * @param high the non-inclusive high element to sort to
         */
        protected MergeSortTask(int[] array, int low, int high) {
            this.array = array;
            this.low = low;
            this.high = high;
        }

        @Override
        protected void compute() {
            if (high - low <= THRESHOLD) {
                Arrays.sort(array, low, high);
            } else {
                int middle = low + ((high - low) >> 1);
                // Execute the sub tasks and wait for them to finish
                invokeAll(new MergeSortTask(array, low, middle), new MergeSortTask(array, middle, high));
                // Then merge the results
                merge(middle);
            }
        }

        /**
         * Merges the two sorted arrays this.low, middle - 1 and middle, this.high - 1
         * @param middle the index in the array where the second sorted list begins
         */
        private void merge(int middle) {
            if (array[middle - 1] < array[middle]) {
                return; // the arrays are already correctly sorted, so we can skip the merge
            }
            int[] copy = new int[high - low];
            System.arraycopy(array, low, copy, 0, copy.length);
            int copyLow = 0;
            int copyHigh = high - low;
            int copyMiddle = middle - low;

            for (int i = low, p = copyLow, q = copyMiddle; i < high; i++) {
                if (q >= copyHigh || (p < copyMiddle && copy[p] < copy[q]) ) {
                    array[i] = copy[p++];
                } else {
                    array[i] = copy[q++];
                }
            }
        }
    }

    /**
     * Creates a {@code MergeSort} containing a ForkJoinPool with the indicated parallelism level
     * @param parallelism the parallelism level used
     */
    public MergeSort(int parallelism) {
        pool = new ForkJoinPool(parallelism);
    }

    /**
     * Sorts all the elements of the given array using the ForkJoin framework
     * @param array the array to sort
     */
    public void sort(int[] array) {
        ForkJoinTask<Void> job = pool.submit(new MergeSortTask(array, 0, array.length));
        job.join();
    }
}
