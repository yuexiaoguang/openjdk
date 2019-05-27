package com.sun.corba.se.impl.protocol.giopmsgheaders;

import org.omg.CORBA.SystemException;
import com.sun.corba.se.spi.ior.IOR;

public interface LocateReplyOrReplyMessage extends Message {

    int getRequestId();
    int getReplyStatus();
    SystemException getSystemException(String message);
    IOR getIOR();
    short getAddrDisposition();
}
