package com.sun.tools.hat.internal.parser;

import java.io.IOException;

/**
 * Positionable read only buffer
 */
public interface ReadBuffer {
    // read methods - only byte array and int primitive types.
    // read position has to be specified always.
    public void  get(long pos, byte[] buf) throws IOException;
    public char  getChar(long pos) throws IOException;
    public byte  getByte(long pos) throws IOException;
    public short getShort(long pos) throws IOException;
    public int   getInt(long pos) throws IOException;
    public long  getLong(long pos) throws IOException;
}
