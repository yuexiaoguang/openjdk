package sun.security.pkcs11.wrapper;



/**
 * class CK_ECDH1_DERIVE_PARAMS provides the parameters to the
 * CKM_ECDH1_DERIVE and CKM_ECDH1_COFACTOR_DERIVE mechanisms.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_ECDH1_DERIVE_PARAMS {
 *   CK_EC_KDF_TYPE kdf;
 *   CK_ULONG ulSharedDataLen;
 *   CK_BYTE_PTR pSharedData;
 *   CK_ULONG ulPublicDataLen;
 *   CK_BYTE_PTR pPublicData;
 * } CK_ECDH1_DERIVE_PARAMS;
 * </PRE>
 */
public class CK_ECDH1_DERIVE_PARAMS {

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_EC_KDF_TYPE kdf;
     * </PRE>
     */
    public long kdf;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulSharedDataLen;
     *   CK_BYTE_PTR pSharedData;
     * </PRE>
     */
    public byte[] pSharedData;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulPublicDataLen;
     *   CK_BYTE_PTR pPublicData;
     * </PRE>
     */
    public byte[] pPublicData;

    public CK_ECDH1_DERIVE_PARAMS(long kdf, byte[] pSharedData, byte[] pPublicData) {
        this.kdf = kdf;
        this.pSharedData = pSharedData;
        this.pPublicData = pPublicData;
    }

    /**
     * Returns the string representation of CK_PKCS5_PBKD2_PARAMS.
     *
     * @return the string representation of CK_PKCS5_PBKD2_PARAMS
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(Constants.INDENT);
        buffer.append("kdf: 0x");
        buffer.append(Functions.toFullHexString(kdf));
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("pSharedDataLen: ");
        buffer.append(pSharedData.length);
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("pSharedData: ");
        buffer.append(Functions.toHexString(pSharedData));
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("pPublicDataLen: ");
        buffer.append(pPublicData.length);
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("pPublicData: ");
        buffer.append(Functions.toHexString(pPublicData));
        //buffer.append(Constants.NEWLINE);

        return buffer.toString();
    }

}
