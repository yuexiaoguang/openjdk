package sun.security.ec;

import java.io.IOException;
import java.math.BigInteger;

import java.security.spec.*;

import sun.security.util.DerOutputStream;
import sun.security.util.ObjectIdentifier;


/**
 * Contains Elliptic Curve parameters.
 */
class NamedCurve extends ECParameterSpec {

    // friendly name for toString() output
    private final String name;

    // well known OID
    private final String oid;

    // encoded form (as NamedCurve identified via OID)
    private final byte[] encoded;

    NamedCurve(String name, String oid, EllipticCurve curve,
            ECPoint g, BigInteger n, int h) {
        super(curve, g, n, h);
        this.name = name;
        this.oid = oid;

        DerOutputStream out = new DerOutputStream();

        try {
            out.putOID(new ObjectIdentifier(oid));
        } catch (IOException e) {
            throw new RuntimeException("Internal error", e);
        }

        encoded = out.toByteArray();
    }

    String getName() {
        return name;
    }

    byte[] getEncoded() {
        return encoded.clone();
    }

    String getObjectId() {
        return oid;
    }

    public String toString() {
        return name + " (" + oid + ")";
    }
}
