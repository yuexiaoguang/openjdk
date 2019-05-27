package com.sun.org.apache.xml.internal.security.signature;

/**
 * Raised if testing the signature value over <i>DigestValue</i> fails because of invalid signature.
 */
public class InvalidSignatureValueException extends XMLSignatureException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor InvalidSignatureValueException
     *
     */
    public InvalidSignatureValueException() {
        super();
    }

    /**
     * Constructor InvalidSignatureValueException
     *
     * @param msgID
     */
    public InvalidSignatureValueException(String msgID) {
        super(msgID);
    }

    /**
     * Constructor InvalidSignatureValueException
     *
     * @param msgID
     * @param exArgs
     */
    public InvalidSignatureValueException(String msgID, Object exArgs[]) {
        super(msgID, exArgs);
    }

    /**
     * Constructor InvalidSignatureValueException
     *
     * @param msgID
     * @param originalException
     */
    public InvalidSignatureValueException(String msgID, Exception originalException) {
        super(msgID, originalException);
    }

    /**
     * Constructor InvalidSignatureValueException
     *
     * @param msgID
     * @param exArgs
     * @param originalException
     */
    public InvalidSignatureValueException(String msgID, Object exArgs[], Exception originalException) {
        super(msgID, exArgs, originalException);
    }
}
