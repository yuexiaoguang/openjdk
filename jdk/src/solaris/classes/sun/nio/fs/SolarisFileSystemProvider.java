package sun.nio.fs;

import java.nio.file.*;
import java.nio.file.attribute.*;
import java.nio.file.spi.FileTypeDetector;
import java.io.IOException;
import java.security.AccessController;
import sun.security.action.GetPropertyAction;

/**
 * Solaris implementation of FileSystemProvider
 */
public class SolarisFileSystemProvider extends UnixFileSystemProvider {
    public SolarisFileSystemProvider() {
        super();
    }

    @Override
    SolarisFileSystem newFileSystem(String dir) {
        return new SolarisFileSystem(this, dir);
    }

    @Override
    SolarisFileStore getFileStore(UnixPath path) throws IOException {
        return new SolarisFileStore(path);
    }


    @Override
    @SuppressWarnings("unchecked")
    public <V extends FileAttributeView> V getFileAttributeView(Path obj,
                                                                Class<V> type,
                                                                LinkOption... options)
    {
        if (type == AclFileAttributeView.class) {
            return (V) new SolarisAclFileAttributeView(UnixPath.toUnixPath(obj),
                                                       Util.followLinks(options));
        }
        if (type == UserDefinedFileAttributeView.class) {
            return(V) new SolarisUserDefinedFileAttributeView(UnixPath.toUnixPath(obj),
                                                              Util.followLinks(options));
        }
        return super.getFileAttributeView(obj, type, options);
    }

    @Override
    public DynamicFileAttributeView getFileAttributeView(Path obj,
                                                         String name,
                                                         LinkOption... options)
    {
        if (name.equals("acl"))
            return new SolarisAclFileAttributeView(UnixPath.toUnixPath(obj),
                                                   Util.followLinks(options));
        if (name.equals("user"))
            return new SolarisUserDefinedFileAttributeView(UnixPath.toUnixPath(obj),
                                                           Util.followLinks(options));
        return super.getFileAttributeView(obj, name, options);
    }

    @Override
    FileTypeDetector getFileTypeDetector() {
        Path userMimeTypes = Paths.get(AccessController.doPrivileged(
            new GetPropertyAction("user.home")), ".mime.types");
        Path etcMimeTypes = Paths.get("/etc/mime.types");

        return chain(new GnomeFileTypeDetector(),
                     new MimeTypesFileTypeDetector(userMimeTypes),
                     new MimeTypesFileTypeDetector(etcMimeTypes));
    }
}
