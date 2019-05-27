package sun.security.krb5.internal;

import sun.security.util.*;
import sun.security.krb5.Asn1Exception;
import java.io.IOException;
import sun.security.krb5.internal.util.KerberosString;

/**
 * Implements the ASN.1 ETYPE-INFO-ENTRY type.
 *
 * ETYPE-INFO-ENTRY     ::= SEQUENCE {
 *         etype        [0] Int32,
 *         salt         [1] OCTET STRING OPTIONAL
 *   }
 */
public class ETypeInfo {

    private int etype;
    private String salt = null;

    private static final byte TAG_TYPE = 0;
    private static final byte TAG_VALUE = 1;

    private ETypeInfo() {
    }

    public ETypeInfo(int etype, String salt) {
        this.etype = etype;
        this.salt = salt;
    }

    public Object clone() {
        return new ETypeInfo(etype, salt);
    }

    /**
     * Constructs a ETypeInfo object.
     * @param encoding a DER-encoded data.
     * @exception Asn1Exception if an error occurs while decoding an
     *            ASN1 encoded data.
     * @exception IOException if an I/O error occurs while reading encoded data.
     */
    public ETypeInfo(DerValue encoding) throws Asn1Exception, IOException {
        DerValue der = null;

        if (encoding.getTag() != DerValue.tag_Sequence) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }

        // etype
        der = encoding.getData().getDerValue();
        if ((der.getTag() & 0x1F) == 0x00) {
            this.etype = der.getData().getBigInteger().intValue();
        }
        else
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);

        // salt
        if (encoding.getData().available() > 0) {
            der = encoding.getData().getDerValue();
            if ((der.getTag() & 0x1F) == 0x01) {
                byte[] saltBytes = der.getData().getOctetString();

                // Although salt is defined as an OCTET STRING, it's the
                // encoding from of a string. As RFC 4120 says:
                //
                // "The salt, ..., is also completely unspecified with respect
                // to character set and is probably locale-specific".
                //
                // It's known that this field is using the same encoding as
                // KerberosString in most implementations.

                if (KerberosString.MSNAME) {
                    this.salt = new String(saltBytes, "UTF8");
                } else {
                    this.salt = new String(saltBytes);
                }
            }
        }

        if (encoding.getData().available() > 0)
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
    }

    /**
     * Encodes this object to an OutputStream.
     *
     * @return byte array of the encoded data.
     * @exception IOException if an I/O error occurs while reading encoded data.
     * @exception Asn1Exception on encoding errors.
     */
    public byte[] asn1Encode() throws Asn1Exception, IOException {

        DerOutputStream bytes = new DerOutputStream();
        DerOutputStream temp = new DerOutputStream();

        temp.putInteger(etype);
        bytes.write(DerValue.createTag(DerValue.TAG_CONTEXT, true,
                                        TAG_TYPE), temp);

        if (salt != null) {
            temp = new DerOutputStream();
            if (KerberosString.MSNAME) {
                temp.putOctetString(salt.getBytes("UTF8"));
            } else {
                temp.putOctetString(salt.getBytes());
            }
            bytes.write(DerValue.createTag(DerValue.TAG_CONTEXT, true,
                                        TAG_VALUE), temp);
        }

        temp = new DerOutputStream();
        temp.write(DerValue.tag_Sequence, bytes);
        return temp.toByteArray();
    }

    // accessor methods
    public int getEType() {
        return etype;
    }

    public String getSalt() {
        return salt;
    }

}
