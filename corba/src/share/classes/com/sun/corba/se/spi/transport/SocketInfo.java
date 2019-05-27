package com.sun.corba.se.spi.transport;

public interface SocketInfo
{
    // Endpoint types known in advance.
    // If you change the value of this constant then update
    // activation.idl accordingly.  It has a duplicate definition
    // to avoid a compilation dependency.

    public static final String IIOP_CLEAR_TEXT = "IIOP_CLEAR_TEXT";


    public String getType();

    public String getHost();

    public int    getPort();
}
