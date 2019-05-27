package sun.nio.fs;

import java.nio.file.*;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * File type detector that does lookup of file extension using Windows Registry.
 */
public class RegistryFileTypeDetector
    extends AbstractFileTypeDetector
{
    public RegistryFileTypeDetector() {
        super();
    }

    @Override
    public String implProbeContentType(Path file) throws IOException {
        if (!(file instanceof Path))
            return null;

        // get file extension
        Path name = file.getFileName();
        if (name == null)
            return null;
        String filename = name.toString();
        int dot = filename.lastIndexOf('.');
        if ((dot < 0) || (dot == (filename.length()-1)))
            return null;

        // query HKEY_CLASSES_ROOT\<ext>
        String key = filename.substring(dot);
        NativeBuffer keyBuffer = WindowsNativeDispatcher.asNativeBuffer(key);
        NativeBuffer nameBuffer = WindowsNativeDispatcher.asNativeBuffer("Content Type");
        try {
            return queryStringValue(keyBuffer.address(), nameBuffer.address());
        } finally {
            nameBuffer.release();
            keyBuffer.release();
        }
    }

    private static native String queryStringValue(long subKey, long name);

    static {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            @Override
            public Void run() {
                // nio.dll has dependency on net.dll
                System.loadLibrary("net");
                System.loadLibrary("nio");
                return null;
        }});
    }
}
