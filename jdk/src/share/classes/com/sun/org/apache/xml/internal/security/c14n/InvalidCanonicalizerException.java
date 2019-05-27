package com.sun.org.apache.xml.internal.security.c14n;

import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;

public class InvalidCanonicalizerException extends XMLSecurityException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor InvalidCanonicalizerException
     *
     */
    public InvalidCanonicalizerException() {
        super();
    }

    /**
     * Constructor InvalidCanonicalizerException
     *
     * @param msgID
     */
    public InvalidCanonicalizerException(String msgID) {
        super(msgID);
    }

    /**
     * Constructor InvalidCanonicalizerException
     *
     * @param msgID
     * @param exArgs
     */
    public InvalidCanonicalizerException(String msgID, Object exArgs[]) {
        super(msgID, exArgs);
    }

    /**
     * Constructor InvalidCanonicalizerException
     *
     * @param msgID
     * @param originalException
     */
    public InvalidCanonicalizerException(String msgID, Exception originalException) {
        super(msgID, originalException);
    }

    /**
     * Constructor InvalidCanonicalizerException
     *
     * @param msgID
     * @param exArgs
     * @param originalException
     */
    public InvalidCanonicalizerException(
        String msgID, Object exArgs[], Exception originalException
    ) {
        super(msgID, exArgs, originalException);
    }
}
