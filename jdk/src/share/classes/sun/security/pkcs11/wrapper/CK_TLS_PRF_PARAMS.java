package sun.security.pkcs11.wrapper;

/**
 * CK_TLS_PRF_PARAMS from PKCS#11 v2.20.
 */
public class CK_TLS_PRF_PARAMS {

    public byte[] pSeed;
    public byte[] pLabel;
    public byte[] pOutput;

    public CK_TLS_PRF_PARAMS(byte[] pSeed, byte[] pLabel, byte[] pOutput) {
        this.pSeed = pSeed;
        this.pLabel = pLabel;
        this.pOutput = pOutput;
    }

}
