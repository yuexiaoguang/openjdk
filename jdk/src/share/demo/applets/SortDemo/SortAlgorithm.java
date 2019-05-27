/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */

/**
 * A generic sort demonstration algorithm
 * SortAlgorithm.java, Thu Oct 27 10:32:35 1994
 */
class SortAlgorithm {

    /**
     * The sort item.
     */
    private SortItem parent;
    /**
     * When true stop sorting.
     */
    protected boolean stopRequested = false;

    /**
     * Set the parent.
     */
    public void setParent(SortItem p) {
        parent = p;
    }

    /**
     * Pause for a while.
     */
    protected void pause() throws Exception {
        if (stopRequested) {
            throw new Exception("Sort Algorithm");
        }
        parent.pause(parent.h1, parent.h2);
    }

    /**
     * Pause for a while and mark item 1.
     */
    protected void pause(int H1) throws Exception {
        if (stopRequested) {
            throw new Exception("Sort Algorithm");
        }
        parent.pause(H1, parent.h2);
    }

    /**
     * Pause for a while and mark item 1 & 2.
     */
    protected void pause(int H1, int H2) throws Exception {
        if (stopRequested) {
            throw new Exception("Sort Algorithm");
        }
        parent.pause(H1, H2);
    }

    /**
     * Stop sorting.
     */
    public void stop() {
        stopRequested = true;
    }

    /**
     * Initialize
     */
    public void init() {
        stopRequested = false;
    }

    /**
     * This method will be called to
     * sort an array of integers.
     */
    void sort(int a[]) throws Exception {
    }
}
