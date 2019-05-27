package com.sun.corba.se.pept.transport;

public interface ListenerThread
{
    public Acceptor getAcceptor();
    public void close();
}
