package sun.nio.fs;

import java.nio.file.attribute.*;
import java.io.IOException;

import static sun.nio.fs.UnixNativeDispatcher.*;
import static sun.nio.fs.SolarisConstants.*;

/**
 * Solaris implementation of FileStore
 */
class SolarisFileStore
    extends UnixFileStore
{
    private final boolean xattrEnabled;

    SolarisFileStore(UnixPath file) throws IOException {
        super(file);
        this.xattrEnabled = xattrEnabled();
    }

    SolarisFileStore(UnixFileSystem fs, UnixMountEntry entry) throws IOException {
        super(fs, entry);
        this.xattrEnabled = xattrEnabled();
    }

    // returns true if extended attributes enabled
    private boolean xattrEnabled() {
        long res = 0L;
        try {
            res = pathconf(file(), _PC_XATTR_ENABLED);
        } catch (UnixException x) {
            // ignore
        }
        return (res != 0L);
    }

    @Override
    UnixMountEntry findMountEntry() throws IOException {
        // On Solaris iterate over the entries in the mount table to find device
        for (UnixMountEntry entry: file().getFileSystem().getMountEntries()) {
            if (entry.dev() == dev()) {
                return entry;
            }
        }
        throw new IOException("Device not found in mnttab");
    }

    @Override
    public boolean supportsFileAttributeView(Class<? extends FileAttributeView> type) {
        if (type == AclFileAttributeView.class) {
            // lookup fstypes.properties
            FeatureStatus status = checkIfFeaturePresent("nfsv4acl");
            switch (status) {
                case PRESENT     : return true;
                case NOT_PRESENT : return false;
                default :
                    // AclFileAttributeView available on ZFS
                    return (type().equals("zfs"));
            }
        }
        if (type == UserDefinedFileAttributeView.class) {
            // lookup fstypes.properties
            FeatureStatus status = checkIfFeaturePresent("xattr");
            switch (status) {
                case PRESENT     : return true;
                case NOT_PRESENT : return false;
                default :
                    // UserDefinedFileAttributeView available if extended
                    // attributes supported
                    return xattrEnabled;
            }
        }
        return super.supportsFileAttributeView(type);
    }

    @Override
    public boolean supportsFileAttributeView(String name) {
        if (name.equals("acl"))
            return supportsFileAttributeView(AclFileAttributeView.class);
        if (name.equals("user"))
            return supportsFileAttributeView(UserDefinedFileAttributeView.class);
        return super.supportsFileAttributeView(name);
    }
}
