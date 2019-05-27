package com.sun.org.apache.xml.internal.security.exceptions;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.MessageFormat;

import com.sun.org.apache.xml.internal.security.utils.Constants;
import com.sun.org.apache.xml.internal.security.utils.I18n;

/**
 * The mother of all Exceptions in this bundle. It allows exceptions to have
 * their messages translated to the different locales.
 *
 * The <code>xmlsecurity_en.properties</code> file contains this line:
 * <pre>
 * xml.WrongElement = Can't create a {0} from a {1} element
 * </pre>
 *
 * Usage in the Java source is:
 * <pre>
 * {
 *    Object exArgs[] = { Constants._TAG_TRANSFORMS, "BadElement" };
 *
 *    throw new XMLSecurityException("xml.WrongElement", exArgs);
 * }
 * </pre>
 *
 * Additionally, if another Exception has been caught, we can supply it, too>
 * <pre>
 * try {
 *    ...
 * } catch (Exception oldEx) {
 *    Object exArgs[] = { Constants._TAG_TRANSFORMS, "BadElement" };
 *
 *    throw new XMLSecurityException("xml.WrongElement", exArgs, oldEx);
 * }
 * </pre>
 */
public class XMLSecurityException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /** Field msgID */
    protected String msgID;

    /**
     * Constructor XMLSecurityException
     *
     */
    public XMLSecurityException() {
        super("Missing message string");

        this.msgID = null;
    }

    /**
     * Constructor XMLSecurityException
     *
     * @param msgID
     */
    public XMLSecurityException(String msgID) {
        super(I18n.getExceptionMessage(msgID));

        this.msgID = msgID;
    }

    /**
     * Constructor XMLSecurityException
     *
     * @param msgID
     * @param exArgs
     */
    public XMLSecurityException(String msgID, Object exArgs[]) {

        super(MessageFormat.format(I18n.getExceptionMessage(msgID), exArgs));

        this.msgID = msgID;
    }

    /**
     * Constructor XMLSecurityException
     *
     * @param originalException
     */
    public XMLSecurityException(Exception originalException) {

        super("Missing message ID to locate message string in resource bundle \""
              + Constants.exceptionMessagesResourceBundleBase
              + "\". Original Exception was a "
              + originalException.getClass().getName() + " and message "
              + originalException.getMessage(), originalException);
    }

    /**
     * Constructor XMLSecurityException
     *
     * @param msgID
     * @param originalException
     */
    public XMLSecurityException(String msgID, Exception originalException) {
        super(I18n.getExceptionMessage(msgID, originalException), originalException);

        this.msgID = msgID;
    }

    /**
     * Constructor XMLSecurityException
     *
     * @param msgID
     * @param exArgs
     * @param originalException
     */
    public XMLSecurityException(String msgID, Object exArgs[], Exception originalException) {
        super(MessageFormat.format(I18n.getExceptionMessage(msgID), exArgs), originalException);

        this.msgID = msgID;
    }

    /**
     * Method getMsgID
     *
     * @return the messageId
     */
    public String getMsgID() {
        if (msgID == null) {
            return "Missing message ID";
        }
        return msgID;
    }

    /** @inheritDoc */
    public String toString() {
        String s = this.getClass().getName();
        String message = super.getLocalizedMessage();

        if (message != null) {
            message = s + ": " + message;
        } else {
            message = s;
        }

        if (super.getCause() != null) {
            message = message + "\nOriginal Exception was " + super.getCause().toString();
        }

        return message;
    }

    /**
     * Method printStackTrace
     *
     */
    public void printStackTrace() {
        synchronized (System.err) {
            super.printStackTrace(System.err);
        }
    }

    /**
     * Method printStackTrace
     *
     * @param printwriter
     */
    public void printStackTrace(PrintWriter printwriter) {
        super.printStackTrace(printwriter);
    }

    /**
     * Method printStackTrace
     *
     * @param printstream
     */
    public void printStackTrace(PrintStream printstream) {
        super.printStackTrace(printstream);
    }

    /**
     * Method getOriginalException
     *
     * @return the original exception
     */
    public Exception getOriginalException() {
        if (this.getCause() instanceof Exception) {
            return (Exception)this.getCause();
        }
        return null;
    }
}