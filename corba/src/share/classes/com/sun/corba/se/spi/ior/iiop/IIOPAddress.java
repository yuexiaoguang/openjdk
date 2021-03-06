package com.sun.corba.se.spi.ior.iiop;

import com.sun.corba.se.spi.ior.Writeable ;

/** IIOPAddress represents the host and port used to establish a
 * TCP connection for an IIOP request.
 */
public interface IIOPAddress extends Writeable
{
    public String getHost() ;

    public int getPort() ;
}
