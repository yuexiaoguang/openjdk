package com.sun.tools.doclets.internal.toolkit;

import java.io.*;

/**
 * The interface for writing class output.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public interface NestedClassWriter {

    /**
     * Close the writer.
     */
    public void close() throws IOException;
}
