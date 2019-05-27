package com.sun.jmx.snmp;

import com.sun.jmx.snmp.SnmpUnknownModelException;

/**
 * This exception is thrown when an <CODE>SnmpSecuritySubSystem</CODE> doesn't know the passed ID.
 * <p><b>This API is a Sun Microsystems internal API  and is subject
 * to change without notice.</b></p>
 */
public class SnmpUnknownSecModelException extends SnmpUnknownModelException {
    private static final long serialVersionUID = -2173491650805292799L;

    /**
     * Constructor.
     * @param msg The exception msg to display.
     */
    public SnmpUnknownSecModelException(String msg) {
        super(msg);
    }
}
