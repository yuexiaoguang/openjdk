package com.sun.jmx.snmp;

import com.sun.jmx.snmp.SnmpUnknownModelException;

/**
 * This exception is thrown when an <CODE>SnmpMsgProcessingSubSystem</CODE> doesn't know the passed ID.
 * <p><b>This API is a Sun Microsystems internal API  and is subject
 * to change without notice.</b></p>
 */
public class SnmpUnknownMsgProcModelException extends SnmpUnknownModelException {
    private static final long serialVersionUID = -4179907244861284771L;

    /**
     * Constructor.
     * @param msg The exception msg to display.
     */
    public SnmpUnknownMsgProcModelException(String msg) {
        super(msg);
    }
}
