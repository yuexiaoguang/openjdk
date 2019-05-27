package sun.security.pkcs11.wrapper;



/**
 * class CK_RSA_PKCS_OAEP_PARAMS provides the parameters to the
 * CKM_RSA_PKCS_OAEP mechanism.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_RSA_PKCS_OAEP_PARAMS {
 *   CK_MECHANISM_TYPE hashAlg;
 *   CK_RSA_PKCS_OAEP_MGF_TYPE mgf;
 *   CK_RSA_PKCS_OAEP_SOURCE_TYPE source;
 *   CK_VOID_PTR pSourceData;
 *   CK_ULONG ulSourceDataLen;
 * } CK_RSA_PKCS_OAEP_PARAMS;
 * </PRE>
 */
public class CK_RSA_PKCS_OAEP_PARAMS {

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_MECHANISM_TYPE hashAlg;
     * </PRE>
     */
    public long hashAlg;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_RSA_PKCS_OAEP_MGF_TYPE mgf;
     * </PRE>
     */
    public long mgf;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_RSA_PKCS_OAEP_SOURCE_TYPE source;
     * </PRE>
     */
    public long source;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VOID_PTR pSourceData;
     *   CK_ULONG ulSourceDataLen;
     * </PRE>
     */
    public byte[] pSourceData;

    //CK_ULONG ulSourceDataLen;
    // ulSourceDataLen == pSourceData.length

    /**
     * Returns the string representation of CK_RSA_PKCS_OAEP_PARAMS.
     *
     * @return the string representation of CK_RSA_PKCS_OAEP_PARAMS
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(Constants.INDENT);
        buffer.append("hashAlg: ");
        buffer.append(hashAlg);
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("mgf: ");
        buffer.append(mgf);
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("source: ");
        buffer.append(source);
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("pSourceData: ");
        buffer.append(pSourceData.toString());
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("pSourceDataLen: ");
        buffer.append(Functions.toHexString(pSourceData));
        //buffer.append(Constants.NEWLINE);

        return buffer.toString() ;
    }

}
