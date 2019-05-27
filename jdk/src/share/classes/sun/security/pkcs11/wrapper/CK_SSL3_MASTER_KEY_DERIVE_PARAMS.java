package sun.security.pkcs11.wrapper;



/**
 * class CK_SSL3_MASTER_KEY_DERIVE_PARAMS provides the parameters to the
 * CKM_SSL3_MASTER_KEY_DERIVE mechanism.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_SSL3_MASTER_KEY_DERIVE_PARAMS {
 *   CK_SSL3_RANDOM_DATA RandomInfo;
 *   CK_VERSION_PTR pVersion;
 * } CK_SSL3_MASTER_KEY_DERIVE_PARAMS;
 * </PRE>
 */
public class CK_SSL3_MASTER_KEY_DERIVE_PARAMS {

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
     *   CK_VERSION_PTR pVersion;
     * </PRE>
     */
    public CK_VERSION pVersion;

    public CK_SSL3_MASTER_KEY_DERIVE_PARAMS(CK_SSL3_RANDOM_DATA random, CK_VERSION version) {
        RandomInfo = random;
        pVersion = version;
    }

    /**
     * Returns the string representation of CK_SSL3_MASTER_KEY_DERIVE_PARAMS.
     *
     * @return the string representation of CK_SSL3_MASTER_KEY_DERIVE_PARAMS
     */
    public String toString() {
        StringBuilder buffer = new StringBuilder();

        buffer.append(Constants.INDENT);
        buffer.append("RandomInfo: ");
        buffer.append(RandomInfo);
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("pVersion: ");
        buffer.append(pVersion);
        //buffer.append(Constants.NEWLINE);

        return buffer.toString();
    }

}
