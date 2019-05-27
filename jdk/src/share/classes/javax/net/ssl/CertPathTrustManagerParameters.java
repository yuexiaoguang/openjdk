package javax.net.ssl;

import java.security.cert.CertPathParameters;

/**
 * A wrapper for CertPathParameters. This class is used to pass validation
 * settings to CertPath based {@link TrustManager}s using the
 * {@link TrustManagerFactory#init(ManagerFactoryParameters)
 * TrustManagerFactory.init()} method.
 *
 * <p>Instances of this class are immutable.
 */
public class CertPathTrustManagerParameters implements ManagerFactoryParameters {

    private final CertPathParameters parameters;

    /**
     * Construct new CertPathTrustManagerParameters from the specified
     * parameters. The parameters are cloned to protect against subsequent
     * modification.
     *
     * @param parameters the CertPathParameters to be used
     *
     * @throws NullPointerException if parameters is null
     */
    public CertPathTrustManagerParameters(CertPathParameters parameters) {
        this.parameters = (CertPathParameters)parameters.clone();
    }

    /**
     * Return a clone of the CertPathParameters encapsulated by this class.
     *
     * @return a clone of the CertPathParameters encapsulated by this class.
     */
    public CertPathParameters getParameters() {
        return (CertPathParameters)parameters.clone();
    }

}
