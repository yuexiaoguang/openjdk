package com.sun.corba.se.pept.transport;

public interface OutboundConnectionCache
    extends ConnectionCache
{
    public Connection get(ContactInfo contactInfo);

    public void put(ContactInfo contactInfo, Connection connection);

    public void remove(ContactInfo contactInfo);
}
