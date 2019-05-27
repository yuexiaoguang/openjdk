package sun.tools.jstat;

import java.util.*;
import sun.jvmstat.monitor.*;

/**
 * Class to compare two Monitor objects by name in descending order.
 */
class DescendingMonitorComparator implements Comparator<Monitor> {
    public int compare(Monitor o1, Monitor o2) {
        String name1 = o1.getName();
        String name2 = o2.getName();
        return name2.compareTo(name1);
    }
}
