/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */

/**
 * A bi-directional bubble sort demonstration algorithm
 * SortAlgorithm.java, Thu Oct 27 10:32:35 1994
 */
class BidirBubbleSortAlgorithm extends SortAlgorithm {

    @Override
    void sort(int a[]) throws Exception {
        int j;
        int limit = a.length;
        int st = -1;
        while (st < limit) {
            st++;
            limit--;
            boolean swapped = false;
            for (j = st; j < limit; j++) {
                if (stopRequested) {
                    return;
                }
                if (a[j] > a[j + 1]) {
                    int T = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = T;
                    swapped = true;
                }
                pause(st, limit);
            }
            if (!swapped) {
                return;
            } else {
                swapped = false;
            }
            for (j = limit; --j >= st;) {
                if (stopRequested) {
                    return;
                }
                if (a[j] > a[j + 1]) {
                    int T = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = T;
                    swapped = true;
                }
                pause(st, limit);
            }
            if (!swapped) {
                return;
            }
        }
    }
}
