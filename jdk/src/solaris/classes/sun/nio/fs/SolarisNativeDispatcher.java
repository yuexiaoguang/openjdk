package sun.nio.fs;

import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * Solaris specific system calls.
 */
class SolarisNativeDispatcher extends UnixNativeDispatcher {
    private SolarisNativeDispatcher() { }

    /**
     * int getextmntent(FILE *fp, struct extmnttab *mp, int len);
     */
    static native int getextmntent(long fp, UnixMountEntry entry)
        throws UnixException;

    /**
     * int facl(int filedes, int cmd, int nentries, void aclbufp)
     */
    static native int facl(int fd, int cmd, int nentries, long aclbufp)
        throws UnixException;


    // initialize
    private static native void init();

    static {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                System.loadLibrary("nio");
                return null;
        }});
        init();
    }
}
