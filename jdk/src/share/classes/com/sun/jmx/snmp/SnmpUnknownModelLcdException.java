package com.sun.jmx.snmp;

/**
 * This exception is thrown when an <CODE>SnmpLcd</CODE> has no ModelLcd associated to the model.
 * <p><b>This API is a Sun Microsystems internal API  and is subject
 * to change without notice.</b></p>
 */
public class SnmpUnknownModelLcdException extends Exception {
    private static final long serialVersionUID = 6369064741633646317L;

    /**
     * Constructor.
     * @param msg The exception msg to display.
     */
    public SnmpUnknownModelLcdException(String msg) {
        super(msg);
    }
}
