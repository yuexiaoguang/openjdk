package com.sun.org.apache.xml.internal.security.signature;

import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;

/**
 * All XML Signature related exceptions inherit herefrom.
 */
public class XMLSignatureException extends XMLSecurityException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor XMLSignatureException
     *
     */
    public XMLSignatureException() {
        super();
    }

    /**
     * Constructor XMLSignatureException
     *
     * @param msgID
     */
    public XMLSignatureException(String msgID) {
        super(msgID);
    }

    /**
     * Constructor XMLSignatureException
     *
     * @param msgID
     * @param exArgs
     */
    public XMLSignatureException(String msgID, Object exArgs[]) {
        super(msgID, exArgs);
    }

    /**
     * Constructor XMLSignatureException
     *
     * @param msgID
     * @param originalException
     */
    public XMLSignatureException(String msgID, Exception originalException) {
        super(msgID, originalException);
    }

    /**
     * Constructor XMLSignatureException
     *
     * @param msgID
     * @param exArgs
     * @param originalException
     */
    public XMLSignatureException(String msgID, Object exArgs[], Exception originalException) {
        super(msgID, exArgs, originalException);
    }
}
