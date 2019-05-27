package com.sun.corba.se.pept.transport;

public interface InboundConnectionCache
    extends ConnectionCache
{
    public Connection get(Acceptor acceptor); // REVISIT

    public void put(Acceptor acceptor, Connection connection);

    public void remove(Connection connection);
}
