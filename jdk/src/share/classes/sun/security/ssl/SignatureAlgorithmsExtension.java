package sun.security.ssl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.net.ssl.SSLProtocolException;

/*
 * [RFC5246] The client uses the "signature_algorithms" extension to
 * indicate to the server which signature/hash algorithm pairs may be
 * used in digital signatures.  The "extension_data" field of this
 * extension contains a "supported_signature_algorithms" value.
 *
 *     enum {
 *         none(0), md5(1), sha1(2), sha224(3), sha256(4), sha384(5),
 *         sha512(6), (255)
 *     } HashAlgorithm;
 *
 *     enum { anonymous(0), rsa(1), dsa(2), ecdsa(3), (255) }
 *       SignatureAlgorithm;
 *
 *     struct {
 *           HashAlgorithm hash;
 *           SignatureAlgorithm signature;
 *     } SignatureAndHashAlgorithm;
 *
 *     SignatureAndHashAlgorithm
 *       supported_signature_algorithms<2..2^16-2>;
 */
final class SignatureAlgorithmsExtension extends HelloExtension {

    private Collection<SignatureAndHashAlgorithm> algorithms;
    private int algorithmsLen;  // length of supported_signature_algorithms

    SignatureAlgorithmsExtension(
            Collection<SignatureAndHashAlgorithm> signAlgs) {

        super(ExtensionType.EXT_SIGNATURE_ALGORITHMS);

        algorithms = new ArrayList<SignatureAndHashAlgorithm>(signAlgs);
        algorithmsLen =
            SignatureAndHashAlgorithm.sizeInRecord() * algorithms.size();
    }

    SignatureAlgorithmsExtension(HandshakeInStream s, int len)
                throws IOException {
        super(ExtensionType.EXT_SIGNATURE_ALGORITHMS);

        algorithmsLen = s.getInt16();
        if (algorithmsLen == 0 || algorithmsLen + 2 != len) {
            throw new SSLProtocolException("Invalid " + type + " extension");
        }

        algorithms = new ArrayList<SignatureAndHashAlgorithm>();
        int remains = algorithmsLen;
        int sequence = 0;
        while (remains > 1) {   // needs at least two bytes
            int hash = s.getInt8();         // hash algorithm
            int signature = s.getInt8();    // signature algorithm

            SignatureAndHashAlgorithm algorithm =
                SignatureAndHashAlgorithm.valueOf(hash, signature, ++sequence);
            algorithms.add(algorithm);
            remains -= 2;  // one byte for hash, one byte for signature
        }

        if (remains != 0) {
            throw new SSLProtocolException("Invalid server_name extension");
        }
    }

    Collection<SignatureAndHashAlgorithm> getSignAlgorithms() {
        return algorithms;
    }

    @Override
    int length() {
        return 6 + algorithmsLen;
    }

    @Override
    void send(HandshakeOutStream s) throws IOException {
        s.putInt16(type.id);
        s.putInt16(algorithmsLen + 2);
        s.putInt16(algorithmsLen);

        for (SignatureAndHashAlgorithm algorithm : algorithms) {
            s.putInt8(algorithm.getHashValue());      // HashAlgorithm
            s.putInt8(algorithm.getSignatureValue()); // SignatureAlgorithm
        }
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        boolean opened = false;
        for (SignatureAndHashAlgorithm signAlg : algorithms) {
            if (opened) {
                buffer.append(", " + signAlg.getAlgorithmName());
            } else {
                buffer.append(signAlg.getAlgorithmName());
                opened = true;
            }
        }

        return "Extension " + type + ", signature_algorithms: " + buffer;
    }
}

