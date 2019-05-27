package org.omg.CORBA;

/**
 * One of the <tt>PolicyErrorCode</tt>s which would be filled if
 * the requested <tt>Policy</tt> is understood to be valid by the
 * ORB, but is not currently supported.
 */
public interface UNSUPPORTED_POLICY {
    /**
     *  The Error code for PolicyError exception.
     */
    final short value = (short) (1L);
};
