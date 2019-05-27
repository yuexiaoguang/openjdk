package com.sun.org.apache.xml.internal.security.keys;

import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;

public class ContentHandlerAlreadyRegisteredException extends XMLSecurityException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor ContentHandlerAlreadyRegisteredException
     *
     */
    public ContentHandlerAlreadyRegisteredException() {
        super();
    }

    /**
     * Constructor ContentHandlerAlreadyRegisteredException
     *
     * @param msgID
     */
    public ContentHandlerAlreadyRegisteredException(String msgID) {
        super(msgID);
    }

    /**
     * Constructor ContentHandlerAlreadyRegisteredException
     *
     * @param msgID
     * @param exArgs
     */
    public ContentHandlerAlreadyRegisteredException(String msgID, Object exArgs[]) {
        super(msgID, exArgs);
    }

    /**
     * Constructor ContentHandlerAlreadyRegisteredException
     *
     * @param msgID
     * @param originalException
     */
    public ContentHandlerAlreadyRegisteredException(String msgID, Exception originalException) {
        super(msgID, originalException);
    }

    /**
     * Constructor ContentHandlerAlreadyRegisteredException
     *
     * @param msgID
     * @param exArgs
     * @param originalException
     */
    public ContentHandlerAlreadyRegisteredException(
        String msgID, Object exArgs[], Exception originalException
    ) {
        super(msgID, exArgs, originalException);
    }

}
