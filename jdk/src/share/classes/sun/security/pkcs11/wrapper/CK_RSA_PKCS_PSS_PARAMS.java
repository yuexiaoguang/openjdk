package sun.security.pkcs11.wrapper;



/**
 * class CK_RSA_PKCS_PSS_PARAMS provides the parameters to the CKM_RSA_PKCS_OAEP
 * mechanism.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_RSA_PKCS_PSS_PARAMS {
 *   CK_MECHANISM_TYPE hashAlg;
 *   CK_RSA_PKCS_MGF_TYPE mgf;
 *   CK_ULONG sLen;
 * } CK_RSA_PKCS_PSS_PARAMS;
 * </PRE>
 */
public class CK_RSA_PKCS_PSS_PARAMS {

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
     *   CK_RSA_PKCS_MGF_TYPE mgf;
     * </PRE>
     */
    public long mgf;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG sLen;
     * </PRE>
     */
    public long sLen;

    /**
     * Returns the string representation of CK_PKCS5_PBKD2_PARAMS.
     *
     * @return the string representation of CK_PKCS5_PBKD2_PARAMS
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(Constants.INDENT);
        buffer.append("hashAlg: 0x");
        buffer.append(Functions.toFullHexString(hashAlg));
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("mgf: 0x");
        buffer.append(Functions.toFullHexString(mgf));
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("sLen: ");
        buffer.append(sLen);
        //buffer.append(Constants.NEWLINE);

        return buffer.toString();
    }

}
