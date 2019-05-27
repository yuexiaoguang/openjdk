package com.sun.crypto.provider;

import java.io.*;
import sun.security.util.*;
import java.math.BigInteger;
import java.security.AlgorithmParametersSpi;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.DHParameterSpec;

/**
 * This class implements the parameter set used by the
 * Diffie-Hellman key agreement as defined in the PKCS #3 standard.
 */
public final class DHParameters extends AlgorithmParametersSpi {

    // The prime (p).
    private BigInteger p = BigInteger.ZERO;

    // The base (g).
    private BigInteger g = BigInteger.ZERO;

    // The private-value length (l)
    private int l = 0;

    protected void engineInit(AlgorithmParameterSpec paramSpec)
        throws InvalidParameterSpecException {
            if (!(paramSpec instanceof DHParameterSpec)) {
                throw new InvalidParameterSpecException
                    ("Inappropriate parameter specification");
            }
            this.p = ((DHParameterSpec)paramSpec).getP();
            this.g = ((DHParameterSpec)paramSpec).getG();
            this.l = ((DHParameterSpec)paramSpec).getL();
    }

    protected void engineInit(byte[] params) throws IOException {
        try {
            DerValue encodedParams = new DerValue(params);

            if (encodedParams.tag != DerValue.tag_Sequence) {
                throw new IOException("DH params parsing error");
            }

            encodedParams.data.reset();

            this.p = encodedParams.data.getBigInteger();
            this.g = encodedParams.data.getBigInteger();

            // Private-value length is OPTIONAL
            if (encodedParams.data.available() != 0) {
                this.l = encodedParams.data.getInteger();
            }

            if (encodedParams.data.available() != 0) {
                throw new IOException
                    ("DH parameter parsing error: Extra data");
            }
        } catch (NumberFormatException e) {
            throw new IOException("Private-value length too big");
        }
    }

    protected void engineInit(byte[] params, String decodingMethod)
        throws IOException {
            engineInit(params);
    }

    protected <T extends AlgorithmParameterSpec>
        T engineGetParameterSpec(Class<T> paramSpec)
        throws InvalidParameterSpecException {

        if (DHParameterSpec.class.isAssignableFrom(paramSpec)) {
            return paramSpec.cast(new DHParameterSpec(this.p, this.g, this.l));
        } else {
            throw new InvalidParameterSpecException
                ("Inappropriate parameter Specification");
        }
    }

    protected byte[] engineGetEncoded() throws IOException {
        DerOutputStream out = new DerOutputStream();
        DerOutputStream bytes = new DerOutputStream();

        bytes.putInteger(this.p);
        bytes.putInteger(this.g);
        // Private-value length is OPTIONAL
        if (this.l > 0) {
            bytes.putInteger(this.l);
        }
        out.write(DerValue.tag_Sequence, bytes);
        return out.toByteArray();
    }

    protected byte[] engineGetEncoded(String encodingMethod)
        throws IOException {
            return engineGetEncoded();
    }

    /*
     * Returns a formatted string describing the parameters.
     */
    protected String engineToString() {
        String LINE_SEP = System.getProperty("line.separator");

        StringBuffer strbuf
            = new StringBuffer("SunJCE Diffie-Hellman Parameters:"
                               + LINE_SEP + "p:" + LINE_SEP
                               + Debug.toHexString(this.p)
                               + LINE_SEP + "g:" + LINE_SEP
                               + Debug.toHexString(this.g));
        if (this.l != 0)
            strbuf.append(LINE_SEP + "l:" + LINE_SEP + "    " + this.l);
        return strbuf.toString();
    }
}
