package com.sun.crypto.provider;

import java.security.*;
import java.security.spec.*;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.DHGenParameterSpec;

/*
 * This class generates parameters for the Diffie-Hellman algorithm.
 * The parameters are a prime, a base, and optionally the length in bits of
 * the private value.
 *
 * <p>The Diffie-Hellman parameter generation accepts the size in bits of the
 * prime modulus and the size in bits of the random exponent as input.
 * The size of the prime modulus defaults to 1024 bits.
 */
public final class DHParameterGenerator
extends AlgorithmParameterGeneratorSpi {

    // The size in bits of the prime modulus
    private int primeSize = 1024;

    // The size in bits of the random exponent (private value)
    private int exponentSize = 0;

    // The source of randomness
    private SecureRandom random = null;

    private static void checkKeySize(int keysize)
        throws InvalidAlgorithmParameterException {
        if ((keysize != 2048) &&
            ((keysize < 512) || (keysize > 1024) || (keysize % 64 != 0))) {
            throw new InvalidAlgorithmParameterException(
                "Keysize must be multiple of 64 ranging from "
                + "512 to 1024 (inclusive), or 2048");
        }
    }

    /**
     * Initializes this parameter generator for a certain keysize
     * and source of randomness.
     * The keysize is specified as the size in bits of the prime modulus.
     *
     * @param keysize the keysize (size of prime modulus) in bits
     * @param random the source of randomness
     */
    protected void engineInit(int keysize, SecureRandom random) {
        // Re-uses DSA parameters and thus have the same range
        try {
            checkKeySize(keysize);
        } catch (InvalidAlgorithmParameterException ex) {
            throw new InvalidParameterException(ex.getMessage());
        }
        this.primeSize = keysize;
        this.random = random;
    }

    /**
     * Initializes this parameter generator with a set of parameter
     * generation values, which specify the size of the prime modulus and
     * the size of the random exponent, both in bits.
     *
     * @param params the set of parameter generation values
     * @param random the source of randomness
     *
     * @exception InvalidAlgorithmParameterException if the given parameter
     * generation values are inappropriate for this parameter generator
     */
    protected void engineInit(AlgorithmParameterSpec genParamSpec,
                              SecureRandom random)
        throws InvalidAlgorithmParameterException {
        if (!(genParamSpec instanceof DHGenParameterSpec)) {
            throw new InvalidAlgorithmParameterException
                ("Inappropriate parameter type");
        }

        DHGenParameterSpec dhParamSpec = (DHGenParameterSpec)genParamSpec;

        primeSize = dhParamSpec.getPrimeSize();

        // Re-uses DSA parameters and thus have the same range
        checkKeySize(primeSize);

        exponentSize = dhParamSpec.getExponentSize();
        if (exponentSize <= 0) {
            throw new InvalidAlgorithmParameterException
                ("Exponent size must be greater than zero");
        }

        // Require exponentSize < primeSize
        if (exponentSize >= primeSize) {
            throw new InvalidAlgorithmParameterException
                ("Exponent size must be less than modulus size");
        }
    }

    /**
     * Generates the parameters.
     *
     * @return the new AlgorithmParameters object
     */
    protected AlgorithmParameters engineGenerateParameters() {
        AlgorithmParameters algParams = null;

        if (this.exponentSize == 0) {
            this.exponentSize = this.primeSize - 1;
        }

        if (this.random == null)
            this.random = SunJCE.getRandom();

        try {
            AlgorithmParameterGenerator paramGen;
            DSAParameterSpec dsaParamSpec;

            paramGen = AlgorithmParameterGenerator.getInstance("DSA");
            paramGen.init(this.primeSize, random);
            algParams = paramGen.generateParameters();
            dsaParamSpec = algParams.getParameterSpec(DSAParameterSpec.class);

            DHParameterSpec dhParamSpec;
            if (this.exponentSize > 0) {
                dhParamSpec = new DHParameterSpec(dsaParamSpec.getP(),
                                                  dsaParamSpec.getG(),
                                                  this.exponentSize);
            } else {
                dhParamSpec = new DHParameterSpec(dsaParamSpec.getP(),
                                                  dsaParamSpec.getG());
            }
            algParams = AlgorithmParameters.getInstance("DH",
                    SunJCE.getInstance());
            algParams.init(dhParamSpec);
        } catch (InvalidParameterSpecException e) {
            // this should never happen
            throw new RuntimeException(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            // this should never happen, because we provide it
            throw new RuntimeException(e.getMessage());
        }
        return algParams;
    }
}