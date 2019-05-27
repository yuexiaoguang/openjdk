package sun.security.pkcs11.wrapper;

import java.math.BigInteger;

import static sun.security.pkcs11.wrapper.PKCS11Constants.*;

/**
 * class CK_ATTRIBUTE includes the type, value and length of an attribute.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_ATTRIBUTE {&nbsp;&nbsp;
 *   CK_ATTRIBUTE_TYPE type;&nbsp;&nbsp;
 *   CK_VOID_PTR pValue;&nbsp;&nbsp;
 *   CK_ULONG ulValueLen;
 * } CK_ATTRIBUTE;
 * </PRE>
 */
public class CK_ATTRIBUTE {

    // common attributes
    // NOTE that CK_ATTRIBUTE is a mutable classes but these attributes
    // *MUST NEVER* be modified, e.g. by using them in a
    // C_GetAttributeValue() call!

    public final static CK_ATTRIBUTE TOKEN_FALSE =
                                    new CK_ATTRIBUTE(CKA_TOKEN, false);

    public final static CK_ATTRIBUTE SENSITIVE_FALSE =
                                    new CK_ATTRIBUTE(CKA_SENSITIVE, false);

    public final static CK_ATTRIBUTE EXTRACTABLE_TRUE =
                                    new CK_ATTRIBUTE(CKA_EXTRACTABLE, true);

    public final static CK_ATTRIBUTE ENCRYPT_TRUE =
                                    new CK_ATTRIBUTE(CKA_ENCRYPT, true);

    public final static CK_ATTRIBUTE DECRYPT_TRUE =
                                    new CK_ATTRIBUTE(CKA_DECRYPT, true);

    public final static CK_ATTRIBUTE WRAP_TRUE =
                                    new CK_ATTRIBUTE(CKA_WRAP, true);

    public final static CK_ATTRIBUTE UNWRAP_TRUE =
                                    new CK_ATTRIBUTE(CKA_UNWRAP, true);

    public final static CK_ATTRIBUTE SIGN_TRUE =
                                    new CK_ATTRIBUTE(CKA_SIGN, true);

    public final static CK_ATTRIBUTE VERIFY_TRUE =
                                    new CK_ATTRIBUTE(CKA_VERIFY, true);

    public final static CK_ATTRIBUTE SIGN_RECOVER_TRUE =
                                    new CK_ATTRIBUTE(CKA_SIGN_RECOVER, true);

    public final static CK_ATTRIBUTE VERIFY_RECOVER_TRUE =
                                    new CK_ATTRIBUTE(CKA_VERIFY_RECOVER, true);

    public final static CK_ATTRIBUTE DERIVE_TRUE =
                                    new CK_ATTRIBUTE(CKA_DERIVE, true);

    public final static CK_ATTRIBUTE ENCRYPT_NULL =
                                    new CK_ATTRIBUTE(CKA_ENCRYPT);

    public final static CK_ATTRIBUTE DECRYPT_NULL =
                                    new CK_ATTRIBUTE(CKA_DECRYPT);

    public final static CK_ATTRIBUTE WRAP_NULL =
                                    new CK_ATTRIBUTE(CKA_WRAP);

    public final static CK_ATTRIBUTE UNWRAP_NULL =
                                    new CK_ATTRIBUTE(CKA_UNWRAP);

    public CK_ATTRIBUTE() {
        // empty
    }

    public CK_ATTRIBUTE(long type) {
        this.type = type;
    }

    public CK_ATTRIBUTE(long type, Object pValue) {
        this.type = type;
        this.pValue = pValue;
    }

    public CK_ATTRIBUTE(long type, boolean value) {
        this.type = type;
        this.pValue = Boolean.valueOf(value);
    }

    public CK_ATTRIBUTE(long type, long value) {
        this.type = type;
        this.pValue = Long.valueOf(value);
    }

    public CK_ATTRIBUTE(long type, BigInteger value) {
        this.type = type;
        this.pValue = sun.security.pkcs11.P11Util.getMagnitude(value);
    }

    public BigInteger getBigInteger() {
        if (pValue instanceof byte[] == false) {
            throw new RuntimeException("Not a byte[]");
        }
        return new BigInteger(1, (byte[])pValue);
    }

    public boolean getBoolean() {
        if (pValue instanceof Boolean == false) {
            throw new RuntimeException
                ("Not a Boolean: " + pValue.getClass().getName());
        }
        return ((Boolean)pValue).booleanValue();
    }

    public char[] getCharArray() {
        if (pValue instanceof char[] == false) {
            throw new RuntimeException("Not a char[]");
        }
        return (char[])pValue;
    }

    public byte[] getByteArray() {
        if (pValue instanceof byte[] == false) {
            throw new RuntimeException("Not a byte[]");
        }
        return (byte[])pValue;
    }

    public long getLong() {
        if (pValue instanceof Long == false) {
            throw new RuntimeException
                ("Not a Long: " + pValue.getClass().getName());
        }
        return ((Long)pValue).longValue();
    }

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ATTRIBUTE_TYPE type;
     * </PRE>
     */
    public long type;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VOID_PTR pValue;
     *   CK_ULONG ulValueLen;
     * </PRE>
     */
    public Object pValue;

    /**
     * Returns the string representation of CK_ATTRIBUTE.
     *
     * @return the string representation of CK_ATTRIBUTE
     */
    public String toString() {
        String prefix = Functions.getAttributeName(type) + " = ";
        if (type == CKA_CLASS) {
            return prefix + Functions.getObjectClassName(getLong());
        } else if (type == CKA_KEY_TYPE) {
            return prefix + Functions.getKeyName(getLong());
        } else {
            String s;
            if (pValue instanceof char[]) {
                s = new String((char[])pValue);
            } else if (pValue instanceof byte[]) {
                s = Functions.toHexString((byte[])pValue);
            } else {
                s = String.valueOf(pValue);
            }
            return prefix + s;
        }
    }

}
