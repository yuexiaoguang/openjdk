/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */

/**
 * A bubble sort demonstration algorithm
 * SortAlgorithm.java, Thu Oct 27 10:32:35 1994
 */
class BubbleSortAlgorithm extends SortAlgorithm {

    @Override
    void sort(int a[]) throws Exception {
        for (int i = a.length; --i >= 0;) {
            boolean swapped = false;
            for (int j = 0; j < i; j++) {
                if (stopRequested) {
                    return;
                }
                if (a[j] > a[j + 1]) {
                    int T = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = T;
                    swapped = true;
                }
                pause(i, j);
            }
            if (!swapped) {
                return;
            }
        }
    }
}
