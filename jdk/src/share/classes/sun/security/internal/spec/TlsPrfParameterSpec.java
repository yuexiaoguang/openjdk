package sun.security.internal.spec;

import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.SecretKey;

/**
 * Parameters for the TLS PRF (pseudo-random function). The PRF function
 * is defined in RFC 2246.
 * This class is used to initialize KeyGenerators of the type "TlsPrf".
 *
 * <p>Instances of this class are immutable.
 *
 * @deprecated Sun JDK internal use only --- WILL BE REMOVED in a future
 * release.
 */
@Deprecated
public class TlsPrfParameterSpec implements AlgorithmParameterSpec {

    private final SecretKey secret;
    private final String label;
    private final byte[] seed;
    private final int outputLength;
    private final String prfHashAlg;
    private final int prfHashLength;
    private final int prfBlockSize;

    /**
     * Constructs a new TlsPrfParameterSpec.
     *
     * @param secret the secret to use in the calculation (or null)
     * @param label the label to use in the calculation
     * @param seed the random seed to use in the calculation
     * @param outputLength the length in bytes of the output key to be produced
     * @param prfHashAlg the name of the TLS PRF hash algorithm to use.
     *        Used only for TLS 1.2+.  TLS1.1 and earlier use a fixed PRF.
     * @param prfHashLength the output length of the TLS PRF hash algorithm.
     *        Used only for TLS 1.2+.
     * @param prfBlockSize the input block size of the TLS PRF hash algorithm.
     *        Used only for TLS 1.2+.
     *
     * @throws NullPointerException if label or seed is null
     * @throws IllegalArgumentException if outputLength is negative
     */
    public TlsPrfParameterSpec(SecretKey secret, String label,
            byte[] seed, int outputLength,
            String prfHashAlg, int prfHashLength, int prfBlockSize) {
        if ((label == null) || (seed == null)) {
            throw new NullPointerException("label and seed must not be null");
        }
        if (outputLength <= 0) {
            throw new IllegalArgumentException("outputLength must be positive");
        }
        this.secret = secret;
        this.label = label;
        this.seed = seed.clone();
        this.outputLength = outputLength;
        this.prfHashAlg = prfHashAlg;
        this.prfHashLength = prfHashLength;
        this.prfBlockSize = prfBlockSize;
    }

    /**
     * Returns the secret to use in the PRF calculation, or null if there is no
     * secret.
     *
     * @return the secret to use in the PRF calculation, or null if there is no
     * secret.
     */
    public SecretKey getSecret() {
        return secret;
    }

    /**
     * Returns the label to use in the PRF calcuation.
     *
     * @return the label to use in the PRF calcuation.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Returns a copy of the seed to use in the PRF calcuation.
     *
     * @return a copy of the seed to use in the PRF calcuation.
     */
    public byte[] getSeed() {
        return seed.clone();
    }

    /**
     * Returns the length in bytes of the output key to be produced.
     *
     * @return the length in bytes of the output key to be produced.
     */
    public int getOutputLength() {
        return outputLength;
    }

    /**
     * Obtains the PRF hash algorithm to use in the PRF calculation.
     *
     * @return the hash algorithm, or null if no algorithm was specified.
     */
    public String getPRFHashAlg() {
        return prfHashAlg;
    }

    /**
     * Obtains the length of PRF hash algorithm.
     *
     * It would have been preferred to use MessageDigest.getDigestLength(),
     * but the API does not require implementations to support the method.
     *
     * @return the hash algorithm length.
     */
    public int getPRFHashLength() {
        return prfHashLength;
    }

    /**
     * Obtains the length of PRF hash algorithm.
     *
     * @return the hash algorithm length.
     */
    public int getPRFBlockSize() {
        return prfBlockSize;
    }
}
