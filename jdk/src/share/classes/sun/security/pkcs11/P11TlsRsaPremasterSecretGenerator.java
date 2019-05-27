package sun.security.pkcs11;

import java.security.*;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.*;
import javax.crypto.spec.*;

import sun.security.internal.spec.TlsRsaPremasterSecretParameterSpec;

import static sun.security.pkcs11.TemplateManager.*;
import sun.security.pkcs11.wrapper.*;
import static sun.security.pkcs11.wrapper.PKCS11Constants.*;

/**
 * KeyGenerator for the SSL/TLS RSA premaster secret.
 */
final class P11TlsRsaPremasterSecretGenerator extends KeyGeneratorSpi {

    private final static String MSG = "TlsRsaPremasterSecretGenerator must be "
        + "initialized using a TlsRsaPremasterSecretParameterSpec";

    // token instance
    private final Token token;

    // algorithm name
    private final String algorithm;

    // mechanism id
    private long mechanism;

    private TlsRsaPremasterSecretParameterSpec spec;

    P11TlsRsaPremasterSecretGenerator(Token token, String algorithm, long mechanism)
            throws PKCS11Exception {
        super();
        this.token = token;
        this.algorithm = algorithm;
        this.mechanism = mechanism;
    }

    protected void engineInit(SecureRandom random) {
        throw new InvalidParameterException(MSG);
    }

    protected void engineInit(AlgorithmParameterSpec params,
            SecureRandom random) throws InvalidAlgorithmParameterException {
        if (params instanceof TlsRsaPremasterSecretParameterSpec == false) {
            throw new InvalidAlgorithmParameterException(MSG);
        }
        this.spec = (TlsRsaPremasterSecretParameterSpec)params;
    }

    protected void engineInit(int keysize, SecureRandom random) {
        throw new InvalidParameterException(MSG);
    }

    protected SecretKey engineGenerateKey() {
        if (spec == null) {
            throw new IllegalStateException
                        ("TlsRsaPremasterSecretGenerator must be initialized");
        }

        byte[] b = spec.getEncodedSecret();
        if (b == null) {
            CK_VERSION version = new CK_VERSION(
                        spec.getMajorVersion(), spec.getMinorVersion());
            Session session = null;
            try {
                session = token.getObjSession();
                CK_ATTRIBUTE[] attributes = token.getAttributes(
                        O_GENERATE, CKO_SECRET_KEY,
                        CKK_GENERIC_SECRET, new CK_ATTRIBUTE[0]);
                long keyID = token.p11.C_GenerateKey(session.id(),
                        new CK_MECHANISM(mechanism, version), attributes);
                SecretKey key = P11Key.secretKey(session,
                        keyID, "TlsRsaPremasterSecret", 48 << 3, attributes);
                return key;
            } catch (PKCS11Exception e) {
                throw new ProviderException(
                        "Could not generate premaster secret", e);
            } finally {
                token.releaseSession(session);
            }
        }

        // Won't worry, the TlsRsaPremasterSecret will be soon converted to
        // TlsMasterSecret.
        return new SecretKeySpec(b, "TlsRsaPremasterSecret");
    }

}
