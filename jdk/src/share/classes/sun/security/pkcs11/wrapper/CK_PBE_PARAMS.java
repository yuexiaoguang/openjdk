package sun.security.pkcs11.wrapper;

/**
 * class CK_PBE_PARAMS provides all of the necessary information required byte
 * the CKM_PBE mechanisms and the CKM_PBA_SHA1_WITH_SHA1_HMAC mechanism.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_PBE_PARAMS {
 *   CK_CHAR_PTR pInitVector;
 *   CK_CHAR_PTR pPassword;
 *   CK_ULONG ulPasswordLen;
 *   CK_CHAR_PTR pSalt;
 *   CK_ULONG ulSaltLen;
 *   CK_ULONG ulIteration;
 * } CK_PBE_PARAMS;
 * </PRE>
 */
public class CK_PBE_PARAMS {

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_CHAR_PTR pInitVector;
     * </PRE>
     */
    public char[] pInitVector;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_CHAR_PTR pPassword;
     *   CK_ULONG ulPasswordLen;
     * </PRE>
     */
    public char[] pPassword;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_CHAR_PTR pSalt
     *   CK_ULONG ulSaltLen;
     * </PRE>
     */
    public char[] pSalt;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulIteration;
     * </PRE>
     */
    public long ulIteration;

    /**
     * Returns the string representation of CK_PBE_PARAMS.
     *
     * @return the string representation of CK_PBE_PARAMS
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(Constants.INDENT);
        buffer.append("pInitVector: ");
        buffer.append(pInitVector);
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("ulPasswordLen: ");
        buffer.append(pPassword.length);
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("pPassword: ");
        buffer.append(pPassword);
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("ulSaltLen: ");
        buffer.append(pSalt.length);
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("pSalt: ");
        buffer.append(pSalt);
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("ulIteration: ");
        buffer.append(ulIteration);
        //buffer.append(Constants.NEWLINE);

        return buffer.toString();
    }

}
