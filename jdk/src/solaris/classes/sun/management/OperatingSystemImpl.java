package sun.management;

/**
 * Implementation class for the operating system.
 * Standard and committed hotspot-specific metrics if any.
 *
 * ManagementFactory.getOperatingSystemMXBean() returns an instance
 * of this class.
 */
class OperatingSystemImpl extends BaseOperatingSystemImpl
    implements com.sun.management.UnixOperatingSystemMXBean {

    OperatingSystemImpl(VMManagement vm) {
        super(vm);
    }

    public native long getCommittedVirtualMemorySize();
    public native long getTotalSwapSpaceSize();
    public native long getFreeSwapSpaceSize();
    public native long getProcessCpuTime();
    public native long getFreePhysicalMemorySize();
    public native long getTotalPhysicalMemorySize();
    public native long getOpenFileDescriptorCount();
    public native long getMaxFileDescriptorCount();
    public native double getSystemCpuLoad();
    public native double getProcessCpuLoad();

    static {
        initialize();
    }
    private static native void initialize();
}
