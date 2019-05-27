package javax.crypto.interfaces;

import java.math.BigInteger;

/**
 * The interface to a PBE key.
 *
 * @since 1.4
 */
public interface PBEKey extends javax.crypto.SecretKey {

    /**
     * The class fingerprint that is set to indicate serialization
     * compatibility since J2SE 1.4.
     */
    static final long serialVersionUID = -1430015993304333921L;

    /**
     * Returns the password.
     *
     * <p> Note: this method should return a copy of the password. It is
     * the caller's responsibility to zero out the password information after
     * it is no longer needed.
     *
     * @return the password.
     */
    char[] getPassword();

    /**
     * Returns the salt or null if not specified.
     *
     * <p> Note: this method should return a copy of the salt. It is
     * the caller's responsibility to zero out the salt information after
     * it is no longer needed.
     *
     * @return the salt.
     */
    byte[] getSalt();

    /**
     * Returns the iteration count or 0 if not specified.
     *
     * @return the iteration count.
     */
    int getIterationCount();
}
