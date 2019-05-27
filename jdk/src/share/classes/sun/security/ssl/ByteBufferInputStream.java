package sun.security.ssl;

import java.io.*;
import java.nio.*;

/**
 * A simple InputStream which uses ByteBuffers as it's backing store.
 * <P>
 * The only IOException should come if the InputStream has been closed.
 * All other IOException should not occur because all the data is local.
 * Data reads on an exhausted ByteBuffer returns a -1.
 */
class ByteBufferInputStream extends InputStream {

    ByteBuffer bb;

    ByteBufferInputStream(ByteBuffer bb) {
        this.bb = bb;
    }

    /**
     * Returns a byte from the ByteBuffer.
     *
     * Increments position().
     */
    @Override
    public int read() throws IOException {

        if (bb == null) {
            throw new IOException("read on a closed InputStream");
        }

        if (bb.remaining() == 0) {
            return -1;
        }
        return bb.get();
    }

    /**
     * Returns a byte array from the ByteBuffer.
     *
     * Increments position().
     */
    @Override
    public int read(byte b[]) throws IOException {

        if (bb == null) {
            throw new IOException("read on a closed InputStream");
        }

        return read(b, 0, b.length);
    }

    /**
     * Returns a byte array from the ByteBuffer.
     *
     * Increments position().
     */
    @Override
    public int read(byte b[], int off, int len) throws IOException {

        if (bb == null) {
            throw new IOException("read on a closed InputStream");
        }

        if (b == null) {
            throw new NullPointerException();
        } else if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return 0;
        }

        int length = Math.min(bb.remaining(), len);
        if (length == 0) {
            return -1;
        }

        bb.get(b, off, length);
        return length;
    }

    /**
     * Skips over and discards <code>n</code> bytes of data from this input
     * stream.
     */
    @Override
    public long skip(long n) throws IOException {

        if (bb == null) {
            throw new IOException("skip on a closed InputStream");
        }

        if (n <= 0) {
            return 0;
        }

        /*
         * ByteBuffers have at most an int, so lose the upper bits.
         * The contract allows this.
         */
        int nInt = (int) n;
        int skip = Math.min(bb.remaining(), nInt);

        bb.position(bb.position() + skip);

        return nInt;
    }

    /**
     * Returns the number of bytes that can be read (or skipped over)
     * from this input stream without blocking by the next caller of a
     * method for this input stream.
     */
    @Override
    public int available() throws IOException {

        if (bb == null) {
            throw new IOException("available on a closed InputStream");
        }

        return bb.remaining();
    }

    /**
     * Closes this input stream and releases any system resources associated
     * with the stream.
     *
     * @exception  IOException  if an I/O error occurs.
     */
    @Override
    public void close() throws IOException {
        bb = null;
    }

    /**
     * Marks the current position in this input stream.
     */
    @Override
    public synchronized void mark(int readlimit) {}

    /**
     * Repositions this stream to the position at the time the
     * <code>mark</code> method was last called on this input stream.
     */
    @Override
    public synchronized void reset() throws IOException {
        throw new IOException("mark/reset not supported");
    }

    /**
     * Tests if this input stream supports the <code>mark</code> and
     * <code>reset</code> methods.
     */
    @Override
    public boolean markSupported() {
        return false;
    }
}
