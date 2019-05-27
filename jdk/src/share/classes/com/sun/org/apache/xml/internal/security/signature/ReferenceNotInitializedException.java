package com.sun.org.apache.xml.internal.security.signature;

/**
 * Raised if verifying a {@link com.sun.org.apache.xml.internal.security.signature.Reference} fails
 * because of an uninitialized {@link com.sun.org.apache.xml.internal.security.signature.XMLSignatureInput}
 */
public class ReferenceNotInitializedException extends XMLSignatureException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor ReferenceNotInitializedException
     *
     */
    public ReferenceNotInitializedException() {
        super();
    }

    /**
     * Constructor ReferenceNotInitializedException
     *
     * @param msgID
     */
    public ReferenceNotInitializedException(String msgID) {
        super(msgID);
    }

    /**
     * Constructor ReferenceNotInitializedException
     *
     * @param msgID
     * @param exArgs
     */
    public ReferenceNotInitializedException(String msgID, Object exArgs[]) {
        super(msgID, exArgs);
    }

    /**
     * Constructor ReferenceNotInitializedException
     *
     * @param msgID
     * @param originalException
     */
    public ReferenceNotInitializedException(String msgID, Exception originalException) {
        super(msgID, originalException);
    }

    /**
     * Constructor ReferenceNotInitializedException
     *
     * @param msgID
     * @param exArgs
     * @param originalException
     */
    public ReferenceNotInitializedException(String msgID, Object exArgs[], Exception originalException) {
        super(msgID, exArgs, originalException);
    }
}
