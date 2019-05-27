package sun.nio.fs;

import java.io.IOException;
import java.nio.file.Path;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * File type detector that uses the libmagic to guess the MIME type of a file.
 */
class MagicFileTypeDetector extends AbstractFileTypeDetector {

    private static final String UNKNOW_MIME_TYPE = "application/octet-stream";

    // true if libmagic is available and successfully loaded
    private final boolean libmagicAvailable;

    public MagicFileTypeDetector() {
        libmagicAvailable = initialize0();
    }

    @Override
    protected String implProbeContentType(Path obj) throws IOException {
        if (!libmagicAvailable || !(obj instanceof UnixPath))
            return null;

        UnixPath path = (UnixPath) obj;
        path.checkRead();

        NativeBuffer buffer = NativeBuffers.asNativeBuffer(path.getByteArrayForSysCalls());
        try {
            byte[] type = probe0(buffer.address());
            String mimeType = (type == null) ? null : new String(type);
            return UNKNOW_MIME_TYPE.equals(mimeType) ? null : mimeType;
        } finally {
            buffer.release();
        }
    }

    private static native boolean initialize0();

    private static native byte[] probe0(long pathAddress);

    static {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            @Override
            public Void run() {
                System.loadLibrary("nio");
                return null;
            }
        });
    }
}
