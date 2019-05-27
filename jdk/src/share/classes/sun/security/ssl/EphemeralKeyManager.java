package sun.security.ssl;

import java.security.*;

/**
 * The "KeyManager" for ephemeral RSA keys. Ephemeral DH and ECDH keys
 * are handled by the DHCrypt and ECDHCrypt classes, respectively.
 */
final class EphemeralKeyManager {

    // indices for the keys array below
    private final static int INDEX_RSA512 = 0;
    private final static int INDEX_RSA1024 = 1;

    /*
     * Current cached RSA KeyPairs. Elements are never null.
     * Indexed via the the constants above.
     */
    private final EphemeralKeyPair[] keys = new EphemeralKeyPair[] {
        new EphemeralKeyPair(null),
        new EphemeralKeyPair(null),
    };

    EphemeralKeyManager() {
        // empty
    }

    /*
     * Get a temporary RSA KeyPair.
     */
    KeyPair getRSAKeyPair(boolean export, SecureRandom random) {
        int length, index;
        if (export) {
            length = 512;
            index = INDEX_RSA512;
        } else {
            length = 1024;
            index = INDEX_RSA1024;
        }

        synchronized (keys) {
            KeyPair kp = keys[index].getKeyPair();
            if (kp == null) {
                try {
                    KeyPairGenerator kgen = JsseJce.getKeyPairGenerator("RSA");
                    kgen.initialize(length, random);
                    keys[index] = new EphemeralKeyPair(kgen.genKeyPair());
                    kp = keys[index].getKeyPair();
                } catch (Exception e) {
                    // ignore
                }
            }
            return kp;
        }
    }

    /**
     * Inner class to handle storage of ephemeral KeyPairs.
     */
    private static class EphemeralKeyPair {

        // maximum number of times a KeyPair is used
        private final static int MAX_USE = 200;

        // maximum time interval in which the keypair is used (1 hour in ms)
        private final static long USE_INTERVAL = 3600*1000;

        private KeyPair keyPair;
        private int uses;
        private long expirationTime;

        private EphemeralKeyPair(KeyPair keyPair) {
            this.keyPair = keyPair;
            expirationTime = System.currentTimeMillis() + USE_INTERVAL;
        }

        /*
         * Check if the KeyPair can still be used.
         */
        private boolean isValid() {
            return (keyPair != null) && (uses < MAX_USE)
                   && (System.currentTimeMillis() < expirationTime);
        }

        /*
         * Return the KeyPair or null if it is invalid.
         */
        private KeyPair getKeyPair() {
            if (isValid() == false) {
                keyPair = null;
                return null;
            }
            uses++;
            return keyPair;
        }
    }
}
