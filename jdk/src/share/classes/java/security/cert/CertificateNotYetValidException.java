package java.security.cert;

/**
 * Certificate is not yet valid exception. This is thrown whenever
 * the current {@code Date} or the specified {@code Date}
 * is before the {@code notBefore} date/time in the Certificate
 * validity period.
 */
public class CertificateNotYetValidException extends CertificateException {

    static final long serialVersionUID = 4355919900041064702L;

    /**
     * Constructs a CertificateNotYetValidException with no detail message. A
     * detail message is a String that describes this particular
     * exception.
     */
    public CertificateNotYetValidException() {
        super();
    }

    /**
     * Constructs a CertificateNotYetValidException with the specified detail
     * message. A detail message is a String that describes this
     * particular exception.
     *
     * @param message the detail message.
     */
    public CertificateNotYetValidException(String message) {
        super(message);
    }
}
