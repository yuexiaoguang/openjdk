package javax.crypto.interfaces;

import java.math.BigInteger;

/**
 * The interface to a Diffie-Hellman private key.
 *
 * @since 1.4
 */
public interface DHPrivateKey extends DHKey, java.security.PrivateKey {

    /**
     * The class fingerprint that is set to indicate serialization
     * compatibility since J2SE 1.4.
     */
    static final long serialVersionUID = 2211791113380396553L;

    /**
     * Returns the private value, <code>x</code>.
     *
     * @return the private value, <code>x</code>
     */
    BigInteger getX();
}
