package com.sun.org.apache.xml.internal.security.c14n;

import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;

/**
 * Class CanonicalizationException
 */
public class CanonicalizationException extends XMLSecurityException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor CanonicalizationException
     *
     */
    public CanonicalizationException() {
        super();
    }

    /**
     * Constructor CanonicalizationException
     *
     * @param msgID
     */
    public CanonicalizationException(String msgID) {
        super(msgID);
    }

    /**
     * Constructor CanonicalizationException
     *
     * @param msgID
     * @param exArgs
     */
    public CanonicalizationException(String msgID, Object exArgs[]) {
        super(msgID, exArgs);
    }

    /**
     * Constructor CanonicalizationException
     *
     * @param msgID
     * @param originalException
     */
    public CanonicalizationException(String msgID, Exception originalException) {
        super(msgID, originalException);
    }

    /**
     * Constructor CanonicalizationException
     *
     * @param msgID
     * @param exArgs
     * @param originalException
     */
    public CanonicalizationException(
        String msgID, Object exArgs[], Exception originalException
    ) {
        super(msgID, exArgs, originalException);
    }
}
