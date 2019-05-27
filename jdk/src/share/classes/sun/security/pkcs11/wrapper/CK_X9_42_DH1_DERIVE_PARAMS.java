package sun.security.pkcs11.wrapper;

/**
 * class CK_X9_42_DH1_DERIVE_PARAMS provides the parameters to the
 * CKM_X9_42_DH_DERIVE mechanism.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_X9_42_DH1_DERIVE_PARAMS {
 *   CK_X9_42_DH_KDF_TYPE kdf;
 *   CK_ULONG ulOtherInfoLen;
 *   CK_BYTE_PTR pOtherInfo;
 *   CK_ULONG ulPublicDataLen;
 *   CK_BYTE_PTR pPublicData;
 * } CK_X9_42_DH1_DERIVE_PARAMS;
 * </PRE>
 */
public class CK_X9_42_DH1_DERIVE_PARAMS {

    /**
     * <B>PKCS#11:</B>
     * <PRE>
    *   CK_X9_42_DH_KDF_TYPE kdf;
     * </PRE>
     */
    public long kdf;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulOtherInfoLen;
     *   CK_BYTE_PTR pOtherInfo;
     * </PRE>
     */
    public byte[] pOtherInfo;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulPublicDataLen;
     *   CK_BYTE_PTR pPublicData;
     * </PRE>
     */
    public byte[] pPublicData;

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
        buffer.append("pOtherInfoLen: ");
        buffer.append(pOtherInfo.length);
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("pOtherInfo: ");
        buffer.append(Functions.toHexString(pOtherInfo));
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
