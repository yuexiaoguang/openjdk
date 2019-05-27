package com.sun.org.apache.xml.internal.security.keys.keyresolver;

import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;

public class KeyResolverException extends XMLSecurityException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor KeyResolverException
     *
     */
    public KeyResolverException() {
        super();
    }

    /**
     * Constructor KeyResolverException
     *
     * @param msgID
     */
    public KeyResolverException(String msgID) {
        super(msgID);
    }

    /**
     * Constructor KeyResolverException
     *
     * @param msgID
     * @param exArgs
     */
    public KeyResolverException(String msgID, Object exArgs[]) {
        super(msgID, exArgs);
    }

    /**
     * Constructor KeyResolverException
     *
     * @param msgID
     * @param originalException
     */
    public KeyResolverException(String msgID, Exception originalException) {
        super(msgID, originalException);
    }

    /**
     * Constructor KeyResolverException
     *
     * @param msgID
     * @param exArgs
     * @param originalException
     */
    public KeyResolverException(String msgID, Object exArgs[], Exception originalException) {
        super(msgID, exArgs, originalException);
    }
}
