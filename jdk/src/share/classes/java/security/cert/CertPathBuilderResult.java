package java.security.cert;

/**
 * A specification of the result of a certification path builder algorithm.
 * All results returned by the {@link CertPathBuilder#build
 * CertPathBuilder.build} method must implement this interface.
 * <p>
 * At a minimum, a {@code CertPathBuilderResult} contains the
 * {@code CertPath} built by the {@code CertPathBuilder} instance.
 * Implementations of this interface may add methods to return implementation
 * or algorithm specific information, such as debugging information or
 * certification path validation results.
 * <p>
 * <b>Concurrent Access</b>
 * <p>
 * Unless otherwise specified, the methods defined in this interface are not
 * thread-safe. Multiple threads that need to access a single
 * object concurrently should synchronize amongst themselves and
 * provide the necessary locking. Multiple threads each manipulating
 * separate objects need not synchronize.
 */
public interface CertPathBuilderResult extends Cloneable {

    /**
     * Returns the built certification path.
     *
     * @return the certification path (never {@code null})
     */
    CertPath getCertPath();

    /**
     * Makes a copy of this {@code CertPathBuilderResult}. Changes to the
     * copy will not affect the original and vice versa.
     *
     * @return a copy of this {@code CertPathBuilderResult}
     */
    Object clone();
}
