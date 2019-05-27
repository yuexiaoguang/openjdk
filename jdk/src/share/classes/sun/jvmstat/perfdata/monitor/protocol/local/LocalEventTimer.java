package sun.jvmstat.perfdata.monitor.protocol.local;

import sun.jvmstat.monitor.*;
import sun.jvmstat.perfdata.monitor.MonitorStatus;
import java.util.*;
import java.util.regex.*;
import java.io.*;

/**
 * Singleton Timer subclass to run polling tasks that generate events
 * for local Java Virtual Machines..
 */
public class LocalEventTimer extends Timer {
    private static LocalEventTimer instance;   // singleton instance

    /**
     * Creates the singleton LocalEventTimer instance.
     */
    private LocalEventTimer() {
        super(true);
    }

    /**
     * Get the singleton LocalEventTimer instance
     *
     * @return LocalEventTimer - the singleton LocalEventTimer instance
     */
    public static synchronized LocalEventTimer getInstance() {
        if (instance == null) {
            instance = new LocalEventTimer();
        }
        return instance;
    }
}
