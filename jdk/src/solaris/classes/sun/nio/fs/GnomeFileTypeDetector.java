package sun.nio.fs;

import java.nio.file.Path;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * File type detector that uses the GNOME I/O library or the deprecated
 * GNOME VFS to guess the MIME type of a file.
 */
public class GnomeFileTypeDetector
    extends AbstractFileTypeDetector
{
    private static final String GNOME_VFS_MIME_TYPE_UNKNOWN =
        "application/octet-stream";

    // true if GIO available
    private final boolean gioAvailable;

    // true if GNOME VFS available and GIO is not available
    private final boolean gnomeVfsAvailable;

    public GnomeFileTypeDetector() {
        gioAvailable = initializeGio();
        if (gioAvailable) {
            gnomeVfsAvailable = false;
        } else {
            gnomeVfsAvailable = initializeGnomeVfs();
        }
    }

    @Override
    public String implProbeContentType(Path obj) throws IOException {
        if (!gioAvailable && !gnomeVfsAvailable)
            return null;
        if (!(obj instanceof UnixPath))
            return null;

        UnixPath path = (UnixPath)obj;
        NativeBuffer buffer = NativeBuffers.asNativeBuffer(path.getByteArrayForSysCalls());
        try {
            if (gioAvailable) {
                // GIO may access file so need permission check
                path.checkRead();
                byte[] type = probeUsingGio(buffer.address());
                return (type == null) ? null : Util.toString(type);
            } else {
                byte[] type = probeUsingGnomeVfs(buffer.address());
                if (type == null)
                    return null;
                String s = Util.toString(type);
                return s.equals(GNOME_VFS_MIME_TYPE_UNKNOWN) ? null : s;
            }
        } finally {
            buffer.release();
        }

    }

    // GIO
    private static native boolean initializeGio();
    private static native byte[] probeUsingGio(long pathAddress);

    // GNOME VFS
    private static native boolean initializeGnomeVfs();
    private static native byte[] probeUsingGnomeVfs(long pathAddress);

    static {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                System.loadLibrary("nio");
                return null;
        }});
    }
}
