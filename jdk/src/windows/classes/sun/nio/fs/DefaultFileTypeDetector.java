package sun.nio.fs;

import java.nio.file.spi.FileTypeDetector;

public class DefaultFileTypeDetector {
    private DefaultFileTypeDetector() { }

    public static FileTypeDetector create() {
        return new RegistryFileTypeDetector();
    }
}
