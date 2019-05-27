package com.sun.corba.se.pept.transport;

public interface ReaderThread {
    public Connection getConnection();
    public void close();
}
