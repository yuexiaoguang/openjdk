package java.security.interfaces;

import java.math.BigInteger;

/**
 * The interface to an RSA public or private key.
 */
public interface RSAKey {

    /**
     * Returns the modulus.
     *
     * @return the modulus
     */
    public BigInteger getModulus();
}
