package sun.misc;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public interface JavaNioAccess {
    /**
     * Provides access to information on buffer usage.
     */
    interface BufferPool {
        String getName();
        long getCount();
        long getTotalCapacity();
        long getMemoryUsed();
    }
    BufferPool getDirectBufferPool();

    /**
     * Constructs a direct ByteBuffer referring to the block of memory starting
     * at the given memory address and and extending {@code cap} bytes.
     * The {@code ob} parameter is an arbitrary object that is attached
     * to the resulting buffer.
     */
    ByteBuffer newDirectByteBuffer(long addr, int cap, Object ob);

    /**
     * Truncates a buffer by changing its capacity to 0.
     */
    void truncate(Buffer buf);

}
