package com.sun.org.apache.xml.internal.security.keys.keyresolver;

import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;

public class InvalidKeyResolverException extends XMLSecurityException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor InvalidKeyResolverException
     *
     */
    public InvalidKeyResolverException() {
        super();
    }

    /**
     * Constructor InvalidKeyResolverException
     *
     * @param msgID
     */
    public InvalidKeyResolverException(String msgID) {
        super(msgID);
    }

    /**
     * Constructor InvalidKeyResolverException
     *
     * @param msgID
     * @param exArgs
     */
    public InvalidKeyResolverException(String msgID, Object exArgs[]) {
        super(msgID, exArgs);
    }

    /**
     * Constructor InvalidKeyResolverException
     *
     * @param msgID
     * @param originalException
     */
    public InvalidKeyResolverException(String msgID, Exception originalException) {
        super(msgID, originalException);
    }

    /**
     * Constructor InvalidKeyResolverException
     *
     * @param msgID
     * @param exArgs
     * @param originalException
     */
    public InvalidKeyResolverException(String msgID, Object exArgs[], Exception originalException) {
        super(msgID, exArgs, originalException);
    }
}
