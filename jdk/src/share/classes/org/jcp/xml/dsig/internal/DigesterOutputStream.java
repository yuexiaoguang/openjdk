package org.jcp.xml.dsig.internal;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;

import com.sun.org.apache.xml.internal.security.utils.UnsyncByteArrayOutputStream;

/**
 * This class has been modified slightly to use java.security.MessageDigest
 * objects as input, rather than
 * com.sun.org.apache.xml.internal.security.algorithms.MessageDigestAlgorithm objects.
 * It also optionally caches the input bytes.
 */
public class DigesterOutputStream extends OutputStream {
    private static java.util.logging.Logger log =
        java.util.logging.Logger.getLogger("org.jcp.xml.dsig.internal");

    private final boolean buffer;
    private UnsyncByteArrayOutputStream bos;
    private final MessageDigest md;

    /**
     * Creates a DigesterOutputStream.
     *
     * @param md the MessageDigest
     */
    public DigesterOutputStream(MessageDigest md) {
        this(md, false);
    }

    /**
     * Creates a DigesterOutputStream.
     *
     * @param md the MessageDigest
     * @param buffer if true, caches the input bytes
     */
    public DigesterOutputStream(MessageDigest md, boolean buffer) {
        this.md = md;
        this.buffer = buffer;
        if (buffer) {
            bos = new UnsyncByteArrayOutputStream();
        }
    }

    public void write(int input) {
        if (buffer) {
            bos.write(input);
        }
        md.update((byte)input);
    }

    @Override
    public void write(byte[] input, int offset, int len) {
        if (buffer) {
            bos.write(input, offset, len);
        }
        if (log.isLoggable(java.util.logging.Level.FINE)) {
            log.log(java.util.logging.Level.FINE, "Pre-digested input:");
            StringBuilder sb = new StringBuilder(len);
            for (int i = offset; i < (offset + len); i++) {
                sb.append((char)input[i]);
            }
            log.log(java.util.logging.Level.FINE, sb.toString());
        }
        md.update(input, offset, len);
    }

    /**
     * @return the digest value
     */
    public byte[] getDigestValue() {
         return md.digest();
    }

    /**
     * @return an input stream containing the cached bytes, or
     *    null if not cached
     */
    public InputStream getInputStream() {
        if (buffer) {
            return new ByteArrayInputStream(bos.toByteArray());
        } else {
            return null;
        }
    }

    @Override
    public void close() throws IOException {
        if (buffer) {
            bos.close();
        }
    }
}
