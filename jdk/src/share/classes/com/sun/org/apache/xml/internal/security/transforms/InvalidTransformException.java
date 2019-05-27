package com.sun.org.apache.xml.internal.security.transforms;

import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;

public class InvalidTransformException extends XMLSecurityException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor InvalidTransformException
     *
     */
    public InvalidTransformException() {
        super();
    }

    /**
     * Constructor InvalidTransformException
     *
     * @param msgId
     */
    public InvalidTransformException(String msgId) {
        super(msgId);
    }

    /**
     * Constructor InvalidTransformException
     *
     * @param msgId
     * @param exArgs
     */
    public InvalidTransformException(String msgId, Object exArgs[]) {
        super(msgId, exArgs);
    }

    /**
     * Constructor InvalidTransformException
     *
     * @param msgId
     * @param originalException
     */
    public InvalidTransformException(String msgId, Exception originalException) {
        super(msgId, originalException);
    }

    /**
     * Constructor InvalidTransformException
     *
     * @param msgId
     * @param exArgs
     * @param originalException
     */
    public InvalidTransformException(String msgId, Object exArgs[], Exception originalException) {
        super(msgId, exArgs, originalException);
    }
}
