package com.sun.jmx.snmp;

/**
 * Interface to be implemented by PDUs that are acknowledged (eg:
 * request, bulk).
 * <p><b>This API is a Sun Microsystems internal API  and is subject
 * to change without notice.</b></p>
 */
public interface SnmpAckPdu {
    /**
     * Returns the PDU to use for the response.
     * @return The response PDU.
     */
    public SnmpPdu getResponsePdu();
}
