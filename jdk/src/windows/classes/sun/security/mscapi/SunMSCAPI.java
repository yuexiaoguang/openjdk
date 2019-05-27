package sun.security.mscapi;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Provider;
import java.util.HashMap;
import java.util.Map;

import sun.security.action.PutAllAction;


/**
 * A Cryptographic Service Provider for the Microsoft Crypto API.
 */
public final class SunMSCAPI extends Provider {

    private static final long serialVersionUID = 8622598936488630849L; //TODO

    private static final String INFO = "Sun's Microsoft Crypto API provider";

    static {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                System.loadLibrary("sunmscapi");
                return null;
            }
        });
    }

    public SunMSCAPI() {
        super("SunMSCAPI", 1.8d, INFO);

        // if there is no security manager installed, put directly into
        // the provider. Otherwise, create a temporary map and use a
        // doPrivileged() call at the end to transfer the contents
        final Map<Object, Object> map =
                (System.getSecurityManager() == null)
                ? this : new HashMap<Object, Object>();

        /*
         * Secure random
         */
        map.put("SecureRandom.Windows-PRNG", "sun.security.mscapi.PRNG");

        /*
         * Key store
         */
        map.put("KeyStore.Windows-MY", "sun.security.mscapi.KeyStore$MY");
        map.put("KeyStore.Windows-ROOT", "sun.security.mscapi.KeyStore$ROOT");

        /*
         * Signature engines
         */
        // NONEwithRSA must be supplied with a pre-computed message digest.
        // Only the following digest algorithms are supported: MD5, SHA-1,
        // SHA-256, SHA-384, SHA-512 and a special-purpose digest
        // algorithm which is a concatenation of SHA-1 and MD5 digests.
        map.put("Signature.NONEwithRSA",
            "sun.security.mscapi.RSASignature$Raw");
        map.put("Signature.SHA1withRSA",
            "sun.security.mscapi.RSASignature$SHA1");
        map.put("Signature.SHA256withRSA",
            "sun.security.mscapi.RSASignature$SHA256");
        map.put("Alg.Alias.Signature.1.2.840.113549.1.1.11",     "SHA256withRSA");
        map.put("Alg.Alias.Signature.OID.1.2.840.113549.1.1.11", "SHA256withRSA");
        map.put("Signature.SHA384withRSA",
            "sun.security.mscapi.RSASignature$SHA384");
        map.put("Alg.Alias.Signature.1.2.840.113549.1.1.12",     "SHA384withRSA");
        map.put("Alg.Alias.Signature.OID.1.2.840.113549.1.1.12", "SHA384withRSA");

        map.put("Signature.SHA512withRSA",
            "sun.security.mscapi.RSASignature$SHA512");
        map.put("Alg.Alias.Signature.1.2.840.113549.1.1.13",     "SHA512withRSA");
        map.put("Alg.Alias.Signature.OID.1.2.840.113549.1.1.13", "SHA512withRSA");

        map.put("Signature.MD5withRSA",
            "sun.security.mscapi.RSASignature$MD5");
        map.put("Signature.MD2withRSA",
            "sun.security.mscapi.RSASignature$MD2");

        // supported key classes
        map.put("Signature.NONEwithRSA SupportedKeyClasses",
            "sun.security.mscapi.Key");
        map.put("Signature.SHA1withRSA SupportedKeyClasses",
            "sun.security.mscapi.Key");
        map.put("Signature.SHA256withRSA SupportedKeyClasses",
            "sun.security.mscapi.Key");
        map.put("Signature.SHA384withRSA SupportedKeyClasses",
            "sun.security.mscapi.Key");
        map.put("Signature.SHA512withRSA SupportedKeyClasses",
            "sun.security.mscapi.Key");
        map.put("Signature.MD5withRSA SupportedKeyClasses",
            "sun.security.mscapi.Key");
        map.put("Signature.MD2withRSA SupportedKeyClasses",
            "sun.security.mscapi.Key");

        /*
         * Key Pair Generator engines
         */
        map.put("KeyPairGenerator.RSA",
            "sun.security.mscapi.RSAKeyPairGenerator");
        map.put("KeyPairGenerator.RSA KeySize", "1024");

        /*
         * Cipher engines
         */
        map.put("Cipher.RSA", "sun.security.mscapi.RSACipher");
        map.put("Cipher.RSA/ECB/PKCS1Padding",
            "sun.security.mscapi.RSACipher");
        map.put("Cipher.RSA SupportedModes", "ECB");
        map.put("Cipher.RSA SupportedPaddings", "PKCS1PADDING");
        map.put("Cipher.RSA SupportedKeyClasses", "sun.security.mscapi.Key");

        if (map != this) {
            AccessController.doPrivileged(new PutAllAction(this, map));
        }
    }
}
