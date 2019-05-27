package java.security.cert;

/**
 * A specification of certification path algorithm parameters.
 * The purpose of this interface is to group (and provide type safety for)
 * all {@code CertPath} parameter specifications. All
 * {@code CertPath} parameter specifications must implement this
 * interface.
 */
public interface CertPathParameters extends Cloneable {

  /**
   * Makes a copy of this {@code CertPathParameters}. Changes to the
   * copy will not affect the original and vice versa.
   *
   * @return a copy of this {@code CertPathParameters}
   */
  Object clone();
}
