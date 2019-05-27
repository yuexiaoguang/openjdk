package com.sun.org.apache.xml.internal.security.signature;

/**
 * Raised when the computed hash value doesn't match the given <i>DigestValue</i>.
 * Additional human readable info is passed to the constructor -- this being the benefit
 * of raising an exception or returning a value.
 */
public class InvalidDigestValueException extends XMLSignatureException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor InvalidDigestValueException
     *
     */
    public InvalidDigestValueException() {
        super();
    }

    /**
     * Constructor InvalidDigestValueException
     *
     * @param msgID
     */
    public InvalidDigestValueException(String msgID) {
        super(msgID);
    }

    /**
     * Constructor InvalidDigestValueException
     *
     * @param msgID
     * @param exArgs
     */
    public InvalidDigestValueException(String msgID, Object exArgs[]) {
        super(msgID, exArgs);
    }

    /**
     * Constructor InvalidDigestValueException
     *
     * @param msgID
     * @param originalException
     */
    public InvalidDigestValueException(String msgID, Exception originalException) {
        super(msgID, originalException);
    }

    /**
     * Constructor InvalidDigestValueException
     *
     * @param msgID
     * @param exArgs
     * @param originalException
     */
    public InvalidDigestValueException(String msgID, Object exArgs[], Exception originalException) {
        super(msgID, exArgs, originalException);
    }
}
