package sun.security.pkcs11.wrapper;



/**
 * class CK_VERSION describes the version of a Cryptoki interface, a Cryptoki
 * library, or an SSL implementation, or the hardware or firmware version of a
 * slot or token.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_VERSION {&nbsp;&nbsp;
 *   CK_BYTE major;&nbsp;&nbsp;
 *   CK_BYTE minor;&nbsp;&nbsp;
 * } CK_VERSION;
 * </PRE>
 */
public class CK_VERSION {

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_BYTE major;
     * </PRE>
     */
    public byte major;  /* integer portion of version number */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_BYTE minor;
     * </PRE>
     */
    public byte minor;  /* 1/100ths portion of version number */

    public CK_VERSION(int major, int minor) {
        this.major = (byte)major;
        this.minor = (byte)minor;
    }

    /**
     * Returns the string representation of CK_VERSION.
     *
     * @return the string representation of CK_VERSION
     */
    public String toString() {
        StringBuilder buffer = new StringBuilder();

        buffer.append(major & 0xff);
        buffer.append('.');
        int m = minor & 0xff;
        if (m < 10) {
            buffer.append('0');
        }
        buffer.append(m);

        return buffer.toString();
    }

}
