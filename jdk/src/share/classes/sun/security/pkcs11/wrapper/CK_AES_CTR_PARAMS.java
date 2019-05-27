package sun.security.pkcs11.wrapper;

/**
 * This class represents the necessary parameters required by
 * the CKM_AES_CTR mechanism as defined in CK_AES_CTR_PARAMS structure.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_AES_CTR_PARAMS {
 *   CK_ULONG ulCounterBits;
 *   CK_BYTE cb[16];
 * } CK_AES_CTR_PARAMS;
 * </PRE>
 */
public class CK_AES_CTR_PARAMS {

    private final long ulCounterBits;
    private final byte cb[];

    public CK_AES_CTR_PARAMS(byte[] cb) {
        ulCounterBits = 128;
        this.cb = cb.clone();
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(Constants.INDENT);
        buffer.append("ulCounterBits: ");
        buffer.append(ulCounterBits);
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("cb: ");
        buffer.append(Functions.toHexString(cb));

        return buffer.toString();
    }
}
