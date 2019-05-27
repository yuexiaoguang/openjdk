package com.sun.org.apache.xml.internal.security.exceptions;

/**
 * This Exception is thrown if decoding of Base64 data fails.
 */
public class Base64DecodingException extends XMLSecurityException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor Base64DecodingException
     *
     */
    public Base64DecodingException() {
        super();
    }

    /**
     * Constructor Base64DecodingException
     *
     * @param msgID
     */
    public Base64DecodingException(String msgID) {
        super(msgID);
    }

    /**
     * Constructor Base64DecodingException
     *
     * @param msgID
     * @param exArgs
     */
    public Base64DecodingException(String msgID, Object exArgs[]) {
        super(msgID, exArgs);
    }

    /**
     * Constructor Base64DecodingException
     *
     * @param msgID
     * @param originalException
     */
    public Base64DecodingException(String msgID, Exception originalException) {
        super(msgID, originalException);
    }

    /**
     * Constructor Base64DecodingException
     *
     * @param msgID
     * @param exArgs
     * @param originalException
     */
    public Base64DecodingException(String msgID, Object exArgs[], Exception originalException) {
        super(msgID, exArgs, originalException);
    }

}
