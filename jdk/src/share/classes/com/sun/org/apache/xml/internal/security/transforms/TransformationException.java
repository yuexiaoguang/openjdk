package com.sun.org.apache.xml.internal.security.transforms;

import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;

public class TransformationException extends XMLSecurityException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor TransformationException
     *
     */
    public TransformationException() {
        super();
    }

    /**
     * Constructor TransformationException
     *
     * @param msgID
     */
    public TransformationException(String msgID) {
        super(msgID);
    }

    /**
     * Constructor TransformationException
     *
     * @param msgID
     * @param exArgs
     */
    public TransformationException(String msgID, Object exArgs[]) {
        super(msgID, exArgs);
    }

    /**
     * Constructor TransformationException
     *
     * @param msgID
     * @param originalException
     */
    public TransformationException(String msgID, Exception originalException) {
        super(msgID, originalException);
    }

    /**
     * Constructor TransformationException
     *
     * @param msgID
     * @param exArgs
     * @param originalException
     */
    public TransformationException(String msgID, Object exArgs[], Exception originalException) {
        super(msgID, exArgs, originalException);
    }
}
