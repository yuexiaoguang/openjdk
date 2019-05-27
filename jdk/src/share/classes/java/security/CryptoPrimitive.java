package java.security;

/**
 * An enumeration of cryptographic primitives.
 */
public enum CryptoPrimitive {
    /**
     * Hash function
     */
    MESSAGE_DIGEST,

    /**
     * Cryptographic random number generator
     */
    SECURE_RANDOM,

    /**
     * Symmetric primitive: block cipher
     */
    BLOCK_CIPHER,

    /**
     * Symmetric primitive: stream cipher
     */
    STREAM_CIPHER,

    /**
     * Symmetric primitive: message authentication code
     */
    MAC,

    /**
     * Symmetric primitive: key wrap
     */
    KEY_WRAP,

    /**
     * Asymmetric primitive: public key encryption
     */
    PUBLIC_KEY_ENCRYPTION,

    /**
     * Asymmetric primitive: signature scheme
     */
    SIGNATURE,

    /**
     * Asymmetric primitive: key encapsulation mechanism
     */
    KEY_ENCAPSULATION,

    /**
     * Asymmetric primitive: key agreement and key distribution
     */
    KEY_AGREEMENT
}
