package com.sun.corba.se.impl.protocol.giopmsgheaders;

import org.omg.CORBA.SystemException;
import com.sun.corba.se.spi.ior.IOR;

/**
 * This interface captures the LocateReplyMessage contract.
 */
public interface LocateReplyMessage extends Message, LocateReplyOrReplyMessage {

    int UNKNOWN_OBJECT = 0;
    int OBJECT_HERE = 1;
    int OBJECT_FORWARD = 2;
    int OBJECT_FORWARD_PERM = 3; // 1.2
    int LOC_SYSTEM_EXCEPTION = 4; // 1.2
    int LOC_NEEDS_ADDRESSING_MODE = 5; // 1.2
}
