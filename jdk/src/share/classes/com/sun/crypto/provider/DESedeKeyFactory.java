package com.sun.crypto.provider;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactorySpi;
import javax.crypto.spec.DESedeKeySpec;
import java.security.InvalidKeyException;
import java.security.spec.KeySpec;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.spec.SecretKeySpec;

/**
 * This class implements the DES-EDE key factory of the Sun provider.
 */
public final class DESedeKeyFactory extends SecretKeyFactorySpi {

    /**
     * Empty constructor
     */
    public DESedeKeyFactory() {
    }

    /**
     * Generates a <code>SecretKey</code> object from the provided key
     * specification (key material).
     *
     * @param keySpec the specification (key material) of the secret key
     *
     * @return the secret key
     *
     * @exception InvalidKeySpecException if the given key specification
     * is inappropriate for this key factory to produce a public key.
     */
    protected SecretKey engineGenerateSecret(KeySpec keySpec)
        throws InvalidKeySpecException {

        try {
            if (keySpec instanceof DESedeKeySpec) {
                return new DESedeKey(((DESedeKeySpec)keySpec).getKey());
            }
            if (keySpec instanceof SecretKeySpec) {
                return new DESedeKey(((SecretKeySpec)keySpec).getEncoded());

            }
            throw new InvalidKeySpecException
                ("Inappropriate key specification");
        } catch (InvalidKeyException e) {
            throw new InvalidKeySpecException(e.getMessage());
        }
    }

    /**
     * Returns a specification (key material) of the given key
     * in the requested format.
     *
     * @param key the key
     *
     * @param keySpec the requested format in which the key material shall be
     * returned
     *
     * @return the underlying key specification (key material) in the
     * requested format
     *
     * @exception InvalidKeySpecException if the requested key specification is
     * inappropriate for the given key, or the given key cannot be processed
     * (e.g., the given key has an unrecognized algorithm or format).
     */
    protected KeySpec engineGetKeySpec(SecretKey key, Class<?> keySpec)
        throws InvalidKeySpecException {

        try {
            if ((key instanceof SecretKey)
                && (key.getAlgorithm().equalsIgnoreCase("DESede"))
                && (key.getFormat().equalsIgnoreCase("RAW"))) {

                // Check if requested key spec is amongst the valid ones
                if (DESedeKeySpec.class.isAssignableFrom(keySpec)) {
                    return new DESedeKeySpec(key.getEncoded());

                } else {
                    throw new InvalidKeySpecException
                        ("Inappropriate key specification");
                }

            } else {
                throw new InvalidKeySpecException
                    ("Inappropriate key format/algorithm");
            }
        } catch (InvalidKeyException e) {
            throw new InvalidKeySpecException("Secret key has wrong size");
        }
    }

    /**
     * Translates a <code>SecretKey</code> object, whose provider may be
     * unknown or potentially untrusted, into a corresponding
     * <code>SecretKey</code> object of this key factory.
     *
     * @param key the key whose provider is unknown or untrusted
     *
     * @return the translated key
     *
     * @exception InvalidKeyException if the given key cannot be processed by
     * this key factory.
     */
    protected SecretKey engineTranslateKey(SecretKey key)
        throws InvalidKeyException {

        try {

            if ((key != null)
                && (key.getAlgorithm().equalsIgnoreCase("DESede"))
                && (key.getFormat().equalsIgnoreCase("RAW"))) {
                // Check if key originates from this factory
                if (key instanceof com.sun.crypto.provider.DESedeKey) {
                    return key;
                }
                // Convert key to spec
                DESedeKeySpec desEdeKeySpec
                    = (DESedeKeySpec)engineGetKeySpec(key,
                                                      DESedeKeySpec.class);
                // Create key from spec, and return it
                return engineGenerateSecret(desEdeKeySpec);

            } else {
                throw new InvalidKeyException
                    ("Inappropriate key format/algorithm");
            }

        } catch (InvalidKeySpecException e) {
            throw new InvalidKeyException("Cannot translate key");
        }
    }
}
