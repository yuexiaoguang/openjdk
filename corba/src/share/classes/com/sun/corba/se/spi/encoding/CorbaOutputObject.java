package com.sun.corba.se.spi.encoding ;

import com.sun.corba.se.pept.encoding.OutputObject ;

import com.sun.corba.se.spi.ior.iiop.GIOPVersion;
import com.sun.corba.se.spi.orb.ORB;
import com.sun.corba.se.spi.transport.CorbaConnection;

import com.sun.corba.se.impl.encoding.CDROutputStream ;
import com.sun.corba.se.impl.encoding.BufferManagerWrite ;


public abstract class CorbaOutputObject
    extends CDROutputStream
    implements OutputObject
{
    public CorbaOutputObject(
        ORB orb, GIOPVersion version, byte encodingVersion,
        boolean littleEndian, BufferManagerWrite bufferManager,
        byte streamFormatVersion, boolean usePooledByteBuffers)
    {
        super(orb, version, encodingVersion, littleEndian, bufferManager,
              streamFormatVersion, usePooledByteBuffers);
    }

    public abstract void writeTo(CorbaConnection connection)
        throws java.io.IOException;
}
