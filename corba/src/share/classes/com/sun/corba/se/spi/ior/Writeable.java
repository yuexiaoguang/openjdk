package com.sun.corba.se.spi.ior;

import org.omg.CORBA_2_3.portable.OutputStream ;

/** This interface represents an entity that can be written to an OutputStream.
 */
public interface Writeable
{
    /** Write this object directly to the output stream.
     */
    void write(OutputStream arg0);
}
