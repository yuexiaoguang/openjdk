package sun.security.pkcs11.wrapper;



/**
 * class CK_SLOT_INFO provides information about a slot.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 *  typedef struct CK_SLOT_INFO {&nbsp;&nbsp;
 *    CK_UTF8CHAR slotDescription[64];&nbsp;&nbsp;
 *    CK_UTF8CHAR manufacturerID[32];&nbsp;&nbsp;
 *    CK_FLAGS flags;&nbsp;&nbsp;
 *    CK_VERSION hardwareVersion;&nbsp;&nbsp;
 *    CK_VERSION firmwareVersion;&nbsp;&nbsp;
 *  } CK_SLOT_INFO;
 * </PRE>
 */
public class CK_SLOT_INFO {

    /* slotDescription and manufacturerID have been changed from
     * CK_CHAR to CK_UTF8CHAR for v2.11. */

    /**
     * must be blank padded and only the first 64 chars will be used<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_UTF8CHAR slotDescription[64];
     * </PRE>
     */
    public char[] slotDescription;

    /**
     * must be blank padded and only the first 32 chars will be used<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_UTF8CHAR manufacturerID[32];
     * </PRE>
     */
    public char[] manufacturerID;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_FLAGS flags;
     * </PRE>
     */
    public long flags;

    /* hardwareVersion and firmwareVersion are new for v2.0 */
    /**
     * version of hardware<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VERSION hardwareVersion;
     * </PRE>
     */
    public CK_VERSION hardwareVersion;

    /**
     * version of firmware<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VERSION firmwareVersion;
     * </PRE>
     */
    public CK_VERSION firmwareVersion;

    public CK_SLOT_INFO(char[] slotDesc, char[] vendor,
                        long flags, CK_VERSION hwVer, CK_VERSION fwVer) {
        this.slotDescription = slotDesc;
        this.manufacturerID = vendor;
        this.flags = flags;
        this.hardwareVersion = hwVer;
        this.firmwareVersion = fwVer;
    }

    /**
     * Returns the string representation of CK_SLOT_INFO.
     *
     * @return the string representation of CK_SLOT_INFO
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(Constants.INDENT);
        buffer.append("slotDescription: ");
        buffer.append(new String(slotDescription));
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("manufacturerID: ");
        buffer.append(new String(manufacturerID));
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("flags: ");
        buffer.append(Functions.slotInfoFlagsToString(flags));
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("hardwareVersion: ");
        buffer.append(hardwareVersion.toString());
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("firmwareVersion: ");
        buffer.append(firmwareVersion.toString());
        //buffer.append(Constants.NEWLINE);

        return buffer.toString() ;
    }

}
