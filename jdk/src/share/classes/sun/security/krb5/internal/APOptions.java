package sun.security.krb5.internal;

import sun.security.krb5.Asn1Exception;
import sun.security.krb5.internal.util.KerberosFlags;
import sun.security.util.*;
import java.io.IOException;

/**
 * Implements the ASN.1 APOptions type.
 *
 * <xmp>
 * APOptions    ::= KerberosFlags
 *      -- reserved(0),
 *      -- use-session-key(1),
 *      -- mutual-required(2)
 * </xmp>
 *
 * KerberosFlags   ::= BIT STRING (SIZE (32..MAX))
 *              -- minimum number of bits shall be sent,
 *              -- but no fewer than 32
 *
 * <p>
 * This definition reflects the Network Working Group RFC4120
 * specification available at
 * <a href="http://www.ietf.org/rfc/rfc4120.txt">
 * http://www.ietf.org/rfc/rfc4120.txt</a>.
 */
public class APOptions extends KerberosFlags {
    public APOptions() {
        super(Krb5.AP_OPTS_MAX + 1);
    }

    public APOptions(int oneBit) throws Asn1Exception {
        super(Krb5.AP_OPTS_MAX + 1);
        set(oneBit, true);
    }
    public APOptions(int size, byte[] data) throws Asn1Exception {
        super(size, data);
        if ((size > data.length * BITS_PER_UNIT) || (size > Krb5.AP_OPTS_MAX + 1)) {
            throw new Asn1Exception(Krb5.BITSTRING_BAD_LENGTH);
        }
    }

    public APOptions(boolean[] data) throws Asn1Exception {
        super(data);
        if (data.length > Krb5.AP_OPTS_MAX + 1) {
            throw new Asn1Exception(Krb5.BITSTRING_BAD_LENGTH);
        }
    }

    public APOptions(DerValue encoding) throws IOException, Asn1Exception {
        this(encoding.getUnalignedBitString(true).toBooleanArray());
    }

    /**
     * Parse (unmarshal) an APOptions from a DER input stream.  This form
     * parsing might be used when expanding a value which is part of
     * a constructed sequence and uses explicitly tagged type.
     *
     * @param data the Der input stream value, which contains one or more marshaled value.
     * @param explicitTag tag number.
     * @param optional indicate if this data field is optional.
     * @return an instance of APOptions.
     * @exception Asn1Exception if an error occurs while decoding an ASN1 encoded data.
     * @exception IOException if an I/O error occurs while reading encoded data.
     *
     */
    public static APOptions parse(DerInputStream data, byte explicitTag, boolean optional) throws Asn1Exception, IOException {
        if ((optional) && (((byte)data.peekByte() & (byte)0x1F) != explicitTag))
            return null;
        DerValue der = data.getDerValue();
        if (explicitTag != (der.getTag() & (byte)0x1F))  {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        } else {
            DerValue subDer = der.getData().getDerValue();
            return new APOptions(subDer);
        }
    }
}
