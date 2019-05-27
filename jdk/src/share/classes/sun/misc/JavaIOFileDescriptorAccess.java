package sun.misc;

import java.io.FileDescriptor;

public interface JavaIOFileDescriptorAccess {
    public void set(FileDescriptor obj, int fd);
    public int get(FileDescriptor fd);

    // Only valid on Windows
    public void setHandle(FileDescriptor obj, long handle);
    public long getHandle(FileDescriptor obj);
}
