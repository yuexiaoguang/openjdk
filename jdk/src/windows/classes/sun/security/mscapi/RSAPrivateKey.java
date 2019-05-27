package sun.security.mscapi;

import java.security.PrivateKey;

/**
 * The handle for an RSA private key using the Microsoft Crypto API.
 */
class RSAPrivateKey extends Key implements PrivateKey
{
    private static final long serialVersionUID = 8113152807912338063L;

    /**
     * Construct an RSAPrivateKey object.
     */
    RSAPrivateKey(long hCryptProv, long hCryptKey, int keyLength)
    {
        super(hCryptProv, hCryptKey, keyLength);
    }

    /**
     * Returns the standard algorithm name for this key. For
     * example, "RSA" would indicate that this key is a RSA key.
     * See Appendix A in the <a href=
     * "../../../guide/security/CryptoSpec.html#AppA">
     * Java Cryptography Architecture API Specification &amp; Reference </a>
     * for information about standard algorithm names.
     *
     * @return the name of the algorithm associated with this key.
     */
    public String getAlgorithm()
    {
        return "RSA";
    }

    public String toString()
    {
        return "RSAPrivateKey [size=" + keyLength + " bits, type=" +
            getKeyType(hCryptKey) + ", container=" +
            getContainerName(hCryptProv) + "]";
    }

    // This class is not serializable
    private void writeObject(java.io.ObjectOutputStream out)
        throws java.io.IOException {

        throw new java.io.NotSerializableException();
    }
}
