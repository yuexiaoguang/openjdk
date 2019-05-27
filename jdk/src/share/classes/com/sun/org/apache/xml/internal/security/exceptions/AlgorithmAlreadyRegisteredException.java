package com.sun.org.apache.xml.internal.security.exceptions;

public class AlgorithmAlreadyRegisteredException extends XMLSecurityException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor AlgorithmAlreadyRegisteredException
     *
     */
    public AlgorithmAlreadyRegisteredException() {
        super();
    }

    /**
     * Constructor AlgorithmAlreadyRegisteredException
     *
     * @param msgID
     */
    public AlgorithmAlreadyRegisteredException(String msgID) {
        super(msgID);
    }

    /**
     * Constructor AlgorithmAlreadyRegisteredException
     *
     * @param msgID
     * @param exArgs
     */
    public AlgorithmAlreadyRegisteredException(String msgID, Object exArgs[]) {
        super(msgID, exArgs);
    }

    /**
     * Constructor AlgorithmAlreadyRegisteredException
     *
     * @param msgID
     * @param originalException
     */
    public AlgorithmAlreadyRegisteredException(String msgID, Exception originalException) {
        super(msgID, originalException);
    }

    /**
     * Constructor AlgorithmAlreadyRegisteredException
     *
     * @param msgID
     * @param exArgs
     * @param originalException
     */
    public AlgorithmAlreadyRegisteredException(
        String msgID, Object exArgs[], Exception originalException
    ) {
        super(msgID, exArgs, originalException);
    }

}
