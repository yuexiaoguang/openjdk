package sun.nio.fs;

import java.nio.file.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.io.IOException;
import java.util.*;

import static sun.nio.fs.UnixNativeDispatcher.*;
import static sun.nio.fs.UnixConstants.*;
import static sun.nio.fs.SolarisConstants.*;

/**
 * Solaris emulation of NamedAttributeView using extended attributes.
 */
class SolarisUserDefinedFileAttributeView
    extends AbstractUserDefinedFileAttributeView
{
    private static final byte[] HERE = { '.' };

    private byte[] nameAsBytes(UnixPath file, String name) throws IOException {
        byte[] bytes = Util.toBytes(name);
        // "", "." and ".." not allowed
        if (bytes.length == 0 || bytes[0] == '.') {
            if (bytes.length <= 1 ||
                (bytes.length == 2 && bytes[1] == '.'))
            {
                throw new FileSystemException(file.getPathForExceptionMessage(),
                    null, "'" + name + "' is not a valid name");
            }
        }
        return bytes;
    }

    private final UnixPath file;
    private final boolean followLinks;

    SolarisUserDefinedFileAttributeView(UnixPath file, boolean followLinks) {
        this.file = file;
        this.followLinks = followLinks;
    }

    @Override
    public List<String> list() throws IOException  {
        if (System.getSecurityManager() != null)
            checkAccess(file.getPathForPermissionCheck(), true, false);

        int fd = file.openForAttributeAccess(followLinks);
        try {
            try {
                // open extended attribute directory
                int dfd = openat(fd, HERE, (O_RDONLY|O_XATTR), 0);
                long dp;
                try {
                    dp = fdopendir(dfd);
                } catch (UnixException x) {
                    close(dfd);
                    throw x;
                }

                // read list of extended attributes
                List<String> list = new ArrayList<>();
                try {
                    byte[] name;
                    while ((name = readdir(dp)) != null) {
                        String s = Util.toString(name);
                        if (!s.equals(".") && !s.equals(".."))
                            list.add(s);
                    }
                } finally {
                    closedir(dp);
                }
                return Collections.unmodifiableList(list);
            } catch (UnixException x) {
                throw new FileSystemException(file.getPathForExceptionMessage(),
                    null, "Unable to get list of extended attributes: " +
                    x.getMessage());
            }
        } finally {
            close(fd);
        }
    }

    @Override
    public int size(String name) throws IOException  {
        if (System.getSecurityManager() != null)
            checkAccess(file.getPathForPermissionCheck(), true, false);

        int fd = file.openForAttributeAccess(followLinks);
        try {
            try {
                // open attribute file
                int afd = openat(fd, nameAsBytes(file,name), (O_RDONLY|O_XATTR), 0);
                try {
                    // read attribute's attributes
                    UnixFileAttributes attrs = UnixFileAttributes.get(afd);
                    long size = attrs.size();
                    if (size > Integer.MAX_VALUE)
                        throw new ArithmeticException("Extended attribute value too large");
                    return (int)size;
                } finally {
                    close(afd);
                }
            } catch (UnixException x) {
                throw new FileSystemException(file.getPathForExceptionMessage(),
                    null, "Unable to get size of extended attribute '" + name +
                    "': " + x.getMessage());
            }
        } finally {
            close(fd);
        }
    }

    @Override
    public int read(String name, ByteBuffer dst) throws IOException {
        if (System.getSecurityManager() != null)
            checkAccess(file.getPathForPermissionCheck(), true, false);

        int fd = file.openForAttributeAccess(followLinks);
        try {
            try {
                // open attribute file
                int afd = openat(fd, nameAsBytes(file,name), (O_RDONLY|O_XATTR), 0);

                // wrap with channel
                FileChannel fc = UnixChannelFactory.newFileChannel(afd, true, false);

                // read to EOF (nothing we can do if I/O error occurs)
                try {
                    if (fc.size() > dst.remaining())
                        throw new IOException("Extended attribute file too large");
                    int total = 0;
                    while (dst.hasRemaining()) {
                        int n = fc.read(dst);
                        if (n < 0)
                            break;
                        total += n;
                    }
                    return total;
                } finally {
                    fc.close();
                }
            } catch (UnixException x) {
                throw new FileSystemException(file.getPathForExceptionMessage(),
                    null, "Unable to read extended attribute '" + name +
                    "': " + x.getMessage());
            }
        } finally {
            close(fd);
        }
    }

    @Override
    public int write(String name, ByteBuffer src) throws IOException {
        if (System.getSecurityManager() != null)
            checkAccess(file.getPathForPermissionCheck(), false, true);

        int fd = file.openForAttributeAccess(followLinks);
        try {
            try {
                // open/create attribute file
                int afd = openat(fd, nameAsBytes(file,name),
                                 (O_CREAT|O_WRONLY|O_TRUNC|O_XATTR),
                                 UnixFileModeAttribute.ALL_PERMISSIONS);

                // wrap with channel
                FileChannel fc = UnixChannelFactory.newFileChannel(afd, false, true);

                // write value (nothing we can do if I/O error occurs)
                try {
                    int rem = src.remaining();
                    while (src.hasRemaining()) {
                        fc.write(src);
                    }
                    return rem;
                } finally {
                    fc.close();
                }
            } catch (UnixException x) {
                throw new FileSystemException(file.getPathForExceptionMessage(),
                    null, "Unable to write extended attribute '" + name +
                    "': " + x.getMessage());
            }
        } finally {
            close(fd);
        }
    }

    @Override
    public void delete(String name) throws IOException {
        if (System.getSecurityManager() != null)
            checkAccess(file.getPathForPermissionCheck(), false, true);

        int fd = file.openForAttributeAccess(followLinks);
        try {
            int dfd = openat(fd, HERE, (O_RDONLY|O_XATTR), 0);
            try {
                unlinkat(dfd, nameAsBytes(file,name), 0);
            } finally {
                close(dfd);
            }
        } catch (UnixException x) {
            throw new FileSystemException(file.getPathForExceptionMessage(),
                null, "Unable to delete extended attribute '" + name +
                "': " + x.getMessage());
        } finally {
            close(fd);
        }
    }

    /**
     * Used by copyTo/moveTo to copy extended attributes from source to target.
     *
     * @param   ofd
     *          file descriptor for source file
     * @param   nfd
     *          file descriptor for target file
     */
    static void copyExtendedAttributes(int ofd, int nfd) {
        try {
            // open extended attribute directory
            int dfd = openat(ofd, HERE, (O_RDONLY|O_XATTR), 0);
            long dp = 0L;
            try {
                dp = fdopendir(dfd);
            } catch (UnixException x) {
                close(dfd);
                throw x;
            }

            // copy each extended attribute
            try {
                byte[] name;
                while ((name = readdir(dp)) != null) {
                    // ignore "." and ".."
                    if (name[0] == '.') {
                        if (name.length == 1)
                            continue;
                        if (name.length == 2 && name[1] == '.')
                            continue;
                    }
                    copyExtendedAttribute(ofd, name, nfd);
                }
            } finally {
                closedir(dp);
            }
        } catch (UnixException ignore) {
        }
    }

    private static void copyExtendedAttribute(int ofd, byte[] name, int nfd)
        throws UnixException
    {
        // open source attribute file
        int src = openat(ofd, name, (O_RDONLY|O_XATTR), 0);
        try {
            // create target attribute file
            int dst = openat(nfd, name, (O_CREAT|O_WRONLY|O_TRUNC|O_XATTR),
                UnixFileModeAttribute.ALL_PERMISSIONS);
            try {
                UnixCopyFile.transfer(dst, src, 0L);
            } finally {
                close(dst);
            }
        } finally {
            close(src);
        }
    }
}
