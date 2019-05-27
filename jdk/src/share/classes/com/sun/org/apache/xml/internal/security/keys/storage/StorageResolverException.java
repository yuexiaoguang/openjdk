package com.sun.org.apache.xml.internal.security.keys.storage;

import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;


public class StorageResolverException extends XMLSecurityException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor StorageResolverException
     *
     */
    public StorageResolverException() {
        super();
    }

    /**
     * Constructor StorageResolverException
     *
     * @param msgID
     */
    public StorageResolverException(String msgID) {
        super(msgID);
    }

    /**
     * Constructor StorageResolverException
     *
     * @param msgID
     * @param exArgs
     */
    public StorageResolverException(String msgID, Object exArgs[]) {
        super(msgID, exArgs);
    }

    /**
     * Constructor StorageResolverException
     *
     * @param msgID
     * @param originalException
     */
    public StorageResolverException(String msgID, Exception originalException) {
        super(msgID, originalException);
    }

    /**
     * Constructor StorageResolverException
     *
     * @param msgID
     * @param exArgs
     * @param originalException
     */
    public StorageResolverException(String msgID, Object exArgs[],
                                    Exception originalException) {
        super(msgID, exArgs, originalException);
    }
}
