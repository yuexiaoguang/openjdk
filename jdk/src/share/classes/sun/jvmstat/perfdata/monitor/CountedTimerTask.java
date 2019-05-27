package sun.jvmstat.perfdata.monitor;

import java.util.*;

/**
 * A TimerTask subclass that keeps a count of the number of executions
 * of the task.
 */
public class CountedTimerTask extends TimerTask {

    volatile long executionCount;

    public long executionCount() {
        return executionCount;
    }

    public void run() {
        executionCount++;
    }
}
