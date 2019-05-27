package com.sun.corba.se.impl.protocol.giopmsgheaders;

import com.sun.corba.se.spi.ior.ObjectKey;

/**
 * This interface captures the LocateRequestMessage contract.
 */
public interface LocateRequestMessage extends Message {
    int getRequestId();
    ObjectKey getObjectKey();
}
