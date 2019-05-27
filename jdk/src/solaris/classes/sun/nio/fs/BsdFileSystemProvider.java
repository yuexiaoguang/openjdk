package sun.nio.fs;

import java.io.IOException;

/**
 * Bsd implementation of FileSystemProvider
 */
public class BsdFileSystemProvider extends UnixFileSystemProvider {
    public BsdFileSystemProvider() {
        super();
    }

    @Override
    BsdFileSystem newFileSystem(String dir) {
        return new BsdFileSystem(this, dir);
    }

    @Override
    BsdFileStore getFileStore(UnixPath path) throws IOException {
        return new BsdFileStore(path);
    }
}
