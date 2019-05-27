package sun.management;

import com.sun.management.OperatingSystemMXBean;

/**
 * Implementation class for the operating system.
 * Standard and committed hotspot-specific metrics if any.
 *
 * ManagementFactory.getOperatingSystemMXBean() returns an instance
 * of this class.
 */
class OperatingSystemImpl extends BaseOperatingSystemImpl
    implements OperatingSystemMXBean {

    // psapiLock is a lock to make sure only one thread loading
    // PSAPI DLL.
    private static Object psapiLock = new Object();

    OperatingSystemImpl(VMManagement vm) {
        super(vm);
    }

    public long getCommittedVirtualMemorySize() {
        synchronized (psapiLock) {
            return getCommittedVirtualMemorySize0();
        }
    }
    private native long getCommittedVirtualMemorySize0();

    public native long getTotalSwapSpaceSize();
    public native long getFreeSwapSpaceSize();
    public native long getProcessCpuTime();
    public native long getFreePhysicalMemorySize();
    public native long getTotalPhysicalMemorySize();
    public native double getSystemCpuLoad();
    public native double getProcessCpuLoad();

    static {
        initialize();
    }
    private static native void initialize();
}
