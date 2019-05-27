package com.sun.org.apache.xml.internal.security.encryption;

import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;

public class XMLEncryptionException extends XMLSecurityException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     *
     */
    public XMLEncryptionException() {
        super();
    }

    /**
     *
     * @param msgID
     */
    public XMLEncryptionException(String msgID) {
        super(msgID);
    }

    /**
     *
     * @param msgID
     * @param exArgs
     */
    public XMLEncryptionException(String msgID, Object exArgs[]) {
        super(msgID, exArgs);
    }

    /**
     *
     * @param msgID
     * @param originalException
     */
    public XMLEncryptionException(String msgID, Exception originalException) {
        super(msgID, originalException);

    }

    /**
     *
     * @param msgID
     * @param exArgs
     * @param originalException
     */
    public XMLEncryptionException(String msgID, Object exArgs[], Exception originalException) {
        super(msgID, exArgs, originalException);
    }
}
