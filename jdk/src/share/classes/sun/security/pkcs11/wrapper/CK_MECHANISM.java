package sun.security.pkcs11.wrapper;

import java.math.BigInteger;
import static sun.security.pkcs11.wrapper.PKCS11Constants.*;

/**
 * class CK_MECHANISM specifies a particular mechanism and any parameters it
 * requires.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 *  typedef struct CK_MECHANISM {&nbsp;&nbsp;
 *    CK_MECHANISM_TYPE mechanism;&nbsp;&nbsp;
 *    CK_VOID_PTR pParameter;&nbsp;&nbsp;
 *    CK_ULONG ulParameterLen;&nbsp;&nbsp;
 *  } CK_MECHANISM;
 * </PRE>
 */
public class CK_MECHANISM {

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_MECHANISM_TYPE mechanism;
     * </PRE>
     */
    public long mechanism;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VOID_PTR pParameter;
     *   CK_ULONG ulParameterLen;
     * </PRE>
     */
    public Object pParameter;

    public CK_MECHANISM() {
        // empty
    }

    public CK_MECHANISM(long mechanism) {
        this.mechanism = mechanism;
    }

    // We don't have a (long,Object) constructor to force type checking.
    // This makes sure we don't accidentally pass a class that the native
    // code cannot handle.

    public CK_MECHANISM(long mechanism, byte[] pParameter) {
        init(mechanism, pParameter);
    }

    public CK_MECHANISM(long mechanism, BigInteger b) {
        init(mechanism, sun.security.pkcs11.P11Util.getMagnitude(b));
    }

    public CK_MECHANISM(long mechanism, CK_VERSION version) {
        init(mechanism, version);
    }

    public CK_MECHANISM(long mechanism, CK_SSL3_MASTER_KEY_DERIVE_PARAMS params) {
        init(mechanism, params);
    }

    public CK_MECHANISM(long mechanism, CK_SSL3_KEY_MAT_PARAMS params) {
        init(mechanism, params);
    }

    public CK_MECHANISM(long mechanism, CK_TLS_PRF_PARAMS params) {
        init(mechanism, params);
    }

    public CK_MECHANISM(long mechanism, CK_ECDH1_DERIVE_PARAMS params) {
        init(mechanism, params);
    }

    public CK_MECHANISM(long mechanism, Long params) {
        init(mechanism, params);
    }

    public CK_MECHANISM(long mechanism, CK_AES_CTR_PARAMS params) {
        init(mechanism, params);
    }

    private void init(long mechanism, Object pParameter) {
        this.mechanism = mechanism;
        this.pParameter = pParameter;
    }

    /**
     * Returns the string representation of CK_MECHANISM.
     *
     * @return the string representation of CK_MECHANISM
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(Constants.INDENT);
        buffer.append("mechanism: ");
        buffer.append(mechanism);
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("pParameter: ");
        buffer.append(pParameter.toString());
        buffer.append(Constants.NEWLINE);

        buffer.append(Constants.INDENT);
        buffer.append("ulParameterLen: ??");
        //buffer.append(pParameter.length);
        //buffer.append(Constants.NEWLINE);

        return buffer.toString() ;
    }

}
