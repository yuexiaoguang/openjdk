package sun.security.mscapi;

/**
 * The handle for an RSA public/private keypair using the Microsoft Crypto API.
 */
class RSAKeyPair {

    private final RSAPrivateKey privateKey;

    private final RSAPublicKey publicKey;

    /**
     * Construct an RSAKeyPair object.
     */
    RSAKeyPair(long hCryptProv, long hCryptKey, int keyLength)
    {
        privateKey = new RSAPrivateKey(hCryptProv, hCryptKey, keyLength);
        publicKey = new RSAPublicKey(hCryptProv, hCryptKey, keyLength);
    }

    public RSAPrivateKey getPrivate() {
        return privateKey;
    }

    public RSAPublicKey getPublic() {
        return publicKey;
    }
}
