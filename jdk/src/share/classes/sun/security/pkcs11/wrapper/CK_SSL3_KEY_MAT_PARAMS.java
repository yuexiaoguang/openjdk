package sun.security.pkcs11.wrapper;



/**
 * class CK_SSL3_KEY_MAT_PARAMS provides the parameters to the
 * CKM_SSL3_KEY_AND_MAC_DERIVE mechanism.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_SSL3_KEY_MAT_PARAMS {
 *   CK_ULONG ulMacSizeInBits;
 *   CK_ULONG ulKeySizeInBits;
 *   CK_ULONG ulIVSizeInBits;
 *   CK_BBOOL bIsExport;
 *   CK_SSL3_RANDOM_DATA RandomInfo;
 *   CK_SSL3_KEY_MAT_OUT_PTR pReturnedKeyMaterial;
 * } CK_SSL3_KEY_MAT_PARAMS;
 * </PRE>
 */
public class CK_SSL3_KEY_MAT_PARAMS{

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulMacSizeInBits;
     * </PRE>
     */
    public long ulMacSizeInBits;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulKeySizeInBits;
     * </PRE>
     */
    public long ulKeySizeInBits;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulIVSizeInBits;
     * </PRE>
     */
    public long ulIVSizeInBits;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_BBOOL bIsExport;
     * </PRE>
     */
    public boolean bIsExport;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_SSL3_RANDOM_DATA RandomInfo;
     * </PRE>
     */
    public CK_SSL3_RANDOM_DATA RandomInfo;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_SSL3_KEY_MAT_OUT_PTR pReturnedKeyMaterial;
     * </PRE>
     */
    public CK_SSL3_KEY_MAT_OUT pReturnedKeyMaterial;

    public CK_SSL3_KEY_MAT_PARAMS(int macSize, int keySize, int ivSize, boolean export, CK_SSL3_RANDOM_DATA random) {
        ulMacSizeInBits = macSize;
        ulKeySizeInBits = keySize;
        ulIVSizeInBits = ivSize;
        bIsExport = export;
        RandomInfo = random;
        pReturnedKeyMaterial = new CK_SSL3_KEY_MAT_OUT();
        if (ivSize != 0) {
            int n = ivSize >> 3;
            pReturnedKeyMaterial.pIVClient = new byte[n];
            pReturnedKeyMaterial.pIVServer = new byte[n];
        }
    }

    /**
     * Returns the string representation of CK_SSL3_KEY_MAT_PARAMS.
     *
     * @return the string representation of CK_SSL3_KEY_MAT_PARAMS
     */
    public String toString() {
        StringBuilder buffer = new StringBuilder();

        buffer.append(Constants.INDENT);
        buffer.append("ulMacSizeInBits: ");
        buffer.append(ulMacSizeInBits);
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("ulKeySizeInBits: ");
        buffer.append(ulKeySizeInBits);
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("ulIVSizeInBits: ");
        buffer.append(ulIVSizeInBits);
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("bIsExport: ");
        buffer.append(bIsExport);
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("RandomInfo: ");
        buffer.append(RandomInfo);
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("pReturnedKeyMaterial: ");
        buffer.append(pReturnedKeyMaterial);
        //buffer.append(Constants.NEWLINE);

        return buffer.toString();
    }

}
