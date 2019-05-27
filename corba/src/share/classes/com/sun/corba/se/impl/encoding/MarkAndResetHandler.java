package com.sun.corba.se.impl.encoding;


/**
 * Defines an abstraction for a RestorableInputStream to
 * implement mark/reset.
 */
interface MarkAndResetHandler
{
    void mark(RestorableInputStream inputStream);

    void fragmentationOccured(ByteBufferWithInfo newFragment);

    void reset();
}
