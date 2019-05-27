package com.sun.corba.se.impl.protocol.giopmsgheaders;

/**
 * This interface captures the FragmentMessage contract.
 */
public interface FragmentMessage extends Message {
    int getRequestId();
    int getHeaderLength();
}
