package com.sun.corba.se.spi.legacy.connection;

import java.util.Collection;

import com.sun.corba.se.spi.legacy.connection.LegacyServerSocketEndPointInfo;
import com.sun.corba.se.spi.transport.SocketOrChannelAcceptor;

public interface LegacyServerSocketManager
{
    public int legacyGetTransientServerPort(String type);
    public int legacyGetPersistentServerPort(String socketType);
    public int legacyGetTransientOrPersistentServerPort(String socketType);

    public LegacyServerSocketEndPointInfo legacyGetEndpoint(String name);

    public boolean legacyIsLocalServerPort(int port);
}
