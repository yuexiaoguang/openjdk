package sun.nio.fs;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.spi.FileTypeDetector;
import java.security.AccessController;
import sun.security.action.GetPropertyAction;

/**
 * MacOSX implementation of FileSystemProvider
 */
public class MacOSXFileSystemProvider extends BsdFileSystemProvider {
    public MacOSXFileSystemProvider() {
        super();
    }

    @Override
    MacOSXFileSystem newFileSystem(String dir) {
        return new MacOSXFileSystem(this, dir);
    }

    @Override
    FileTypeDetector getFileTypeDetector() {
        Path userMimeTypes = Paths.get(AccessController.doPrivileged(
            new GetPropertyAction("user.home")), ".mime.types");
        return new MimeTypesFileTypeDetector(userMimeTypes);
    }
}
