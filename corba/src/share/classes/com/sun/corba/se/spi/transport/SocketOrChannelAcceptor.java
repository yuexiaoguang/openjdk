package com.sun.corba.se.spi.transport;

import java.net.ServerSocket;

public interface SocketOrChannelAcceptor
{
    public ServerSocket getServerSocket();
}
