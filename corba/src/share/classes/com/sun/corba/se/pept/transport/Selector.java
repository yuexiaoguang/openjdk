package com.sun.corba.se.pept.transport;

public interface Selector
{
    public void setTimeout(long timeout);
    public long getTimeout();
    public void registerInterestOps(EventHandler eventHandler);
    public void registerForEvent(EventHandler eventHander);
    public void unregisterForEvent(EventHandler eventHandler);
    public void close();
}
