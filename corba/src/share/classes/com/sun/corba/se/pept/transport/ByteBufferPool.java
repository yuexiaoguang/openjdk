package com.sun.corba.se.pept.transport;

import java.nio.ByteBuffer;

public interface ByteBufferPool
{
    public ByteBuffer getByteBuffer(int theSize);
    public void releaseByteBuffer(ByteBuffer thebb);
    public int activeCount();
}
