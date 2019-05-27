package sun.security.pkcs11.wrapper;



/**
 * class  CK_INFO provides general information about Cryptoki.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 *  typedef struct CK_INFO {&nbsp;&nbsp;
 *    CK_VERSION cryptokiVersion;&nbsp;&nbsp;
 *    CK_UTF8CHAR manufacturerID[32];&nbsp;&nbsp;
 *    CK_FLAGS flags;&nbsp;&nbsp;
 *    CK_UTF8CHAR libraryDescription[32];&nbsp;&nbsp;
 *    CK_VERSION libraryVersion;&nbsp;&nbsp;
 *  } CK_INFO;
 * </PRE>
 */
public class CK_INFO {

    /**
     * Cryptoki interface version number<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VERSION cryptokiVersion;
     * </PRE>
     */
    public CK_VERSION cryptokiVersion;

    /**
     * ID of the Cryptoki library manufacturer. must be blank
     * padded - only the first 32 chars will be used<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_UTF8CHAR manufacturerID[32];
     * </PRE>
     */
    public char[] manufacturerID;

    /**
     * bit flags reserved for future versions. must be zero<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_FLAGS flags;
     * </PRE>
     */
    public long flags;


/* libraryDescription and libraryVersion are new for v2.0 */

    /**
     * must be blank padded - only the first 32 chars will be used<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_UTF8CHAR libraryDescription[32];
     * </PRE>
     */
    public char[] libraryDescription;

    /**
     * Cryptoki library version number<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VERSION libraryVersion;
     * </PRE>
     */
    public CK_VERSION libraryVersion;

    public CK_INFO(CK_VERSION cryptoVer, char[] vendor, long flags,
                   char[] libDesc, CK_VERSION libVer) {
        this.cryptokiVersion = cryptoVer;
        this.manufacturerID = vendor;
        this.flags = flags;
        this.libraryDescription = libDesc;
        this.libraryVersion = libVer;
    }

    /**
     * Returns the string representation of CK_INFO.
     *
     * @return the string representation of CK_INFO
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(Constants.INDENT);
        buffer.append("cryptokiVersion: ");
        buffer.append(cryptokiVersion.toString());
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("manufacturerID: ");
        buffer.append(new String(manufacturerID));
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("flags: ");
        buffer.append(Functions.toBinaryString(flags));
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("libraryDescription: ");
        buffer.append(new String(libraryDescription));
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("libraryVersion: ");
        buffer.append(libraryVersion.toString());
        //buffer.append(Constants.NEWLINE);

        return buffer.toString() ;
    }

}
