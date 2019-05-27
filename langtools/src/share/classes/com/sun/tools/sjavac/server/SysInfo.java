/**
 * A utility class used to report information about the system
 * where the javac server is running.
 *
 * <p><b>This is NOT part of any supported API.
 * If you write code that depends on this, you do so at your own
 * risk.  This code and its internal interfaces are subject to change
 * or deletion without notice.</b></p>
 */
package com.sun.tools.sjavac.server;

public class SysInfo {
    public int numCores;
    public long maxMemory;

    public SysInfo(int nc, long mm) {
        numCores = nc;
        maxMemory = mm;
    }
}
