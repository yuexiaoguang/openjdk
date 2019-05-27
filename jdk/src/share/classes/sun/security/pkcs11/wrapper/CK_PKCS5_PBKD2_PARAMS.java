package sun.security.pkcs11.wrapper;



/**
 * class CK_PKCS5_PBKD2_PARAMS provides the parameters to the CKM_PKCS5_PBKD2
 * mechanism.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_PKCS5_PBKD2_PARAMS {
 *   CK_PKCS5_PBKD2_SALT_SOURCE_TYPE saltSource;
 *   CK_VOID_PTR pSaltSourceData;
 *   CK_ULONG ulSaltSourceDataLen;
 *   CK_ULONG iterations;
 *   CK_PKCS5_PBKD2_PSEUDO_RANDOM_FUNCTION_TYPE prf;
 *   CK_VOID_PTR pPrfData;
 *   CK_ULONG ulPrfDataLen;
 * } CK_PKCS5_PBKD2_PARAMS;
 * </PRE>
 */
public class CK_PKCS5_PBKD2_PARAMS {

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_PKCS5_PBKDF2_SALT_SOURCE_TYPE saltSource;
     * </PRE>
     */
    public long saltSource;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VOID_PTR pSaltSourceData;
     *   CK_ULONG ulSaltSourceDataLen;
     * </PRE>
     */
    public byte[] pSaltSourceData;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG iterations;
     * </PRE>
     */
    public long iterations;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_PKCS5_PBKD2_PSEUDO_RANDOM_FUNCTION_TYPE prf;
     * </PRE>
     */
    public long prf;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VOID_PTR pPrfData;
     *   CK_ULONG ulPrfDataLen;
     * </PRE>
     */
    public byte[] pPrfData;

    /**
     * Returns the string representation of CK_PKCS5_PBKD2_PARAMS.
     *
     * @return the string representation of CK_PKCS5_PBKD2_PARAMS
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(Constants.INDENT);
        buffer.append("saltSource: ");
        buffer.append(saltSource);
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("pSaltSourceData: ");
        buffer.append(Functions.toHexString(pSaltSourceData));
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("ulSaltSourceDataLen: ");
        buffer.append(pSaltSourceData.length);
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("iterations: ");
        buffer.append(iterations);
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("prf: ");
        buffer.append(prf);
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("pPrfData: ");
        buffer.append(Functions.toHexString(pPrfData));
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("ulPrfDataLen: ");
        buffer.append(pPrfData.length);
        //buffer.append(Constants.NEWLINE);

        return buffer.toString();
    }

}
