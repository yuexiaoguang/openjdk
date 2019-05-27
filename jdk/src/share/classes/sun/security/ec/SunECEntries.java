package sun.security.ec;

import java.util.Collection;
import java.util.Map;

import java.util.regex.Pattern;

/**
 * Defines the entries of the SunEC provider.
 */
final class SunECEntries {

    private SunECEntries() {
        // empty
    }

    static void putEntries(Map<Object, Object> map,
        boolean useFullImplementation) {

        /*
         *  Key Factory engine
         */
        map.put("KeyFactory.EC", "sun.security.ec.ECKeyFactory");
        map.put("Alg.Alias.KeyFactory.EllipticCurve", "EC");

        map.put("KeyFactory.EC ImplementedIn", "Software");

        /*
         * Algorithm Parameter engine
         */
        map.put("AlgorithmParameters.EC", "sun.security.ec.ECParameters");
        map.put("Alg.Alias.AlgorithmParameters.EllipticCurve", "EC");
        map.put("Alg.Alias.AlgorithmParameters.1.2.840.10045.2.1", "EC");

        map.put("AlgorithmParameters.EC KeySize", "256");

        map.put("AlgorithmParameters.EC ImplementedIn", "Software");

        // "AlgorithmParameters.EC SupportedCurves" prop used by unit test
        boolean firstCurve = true;
        StringBuilder names = new StringBuilder();
        Pattern nameSplitPattern = Pattern.compile(CurveDB.SPLIT_PATTERN);

        Collection<? extends NamedCurve> supportedCurves =
            CurveDB.getSupportedCurves();
        for (NamedCurve namedCurve : supportedCurves) {
            if (!firstCurve) {
                names.append("|");
            } else {
                firstCurve = false;
            }

            names.append("[");

            String[] commonNames = nameSplitPattern.split(namedCurve.getName());
            for (String commonName : commonNames) {
                names.append(commonName.trim());
                names.append(",");
            }

            names.append(namedCurve.getObjectId());
            names.append("]");
        }

        map.put("AlgorithmParameters.EC SupportedCurves", names.toString());

        /*
         * Register the algorithms below only when the full ECC implementation
         * is available
         */
        if (!useFullImplementation) {
            return;
        }

        /*
         * Signature engines
         */
        map.put("Signature.NONEwithECDSA",
            "sun.security.ec.ECDSASignature$Raw");
        map.put("Signature.SHA1withECDSA",
            "sun.security.ec.ECDSASignature$SHA1");
        map.put("Alg.Alias.Signature.OID.1.2.840.10045.4.1", "SHA1withECDSA");
        map.put("Alg.Alias.Signature.1.2.840.10045.4.1", "SHA1withECDSA");

        map.put("Signature.SHA224withECDSA",
            "sun.security.ec.ECDSASignature$SHA224");
        map.put("Alg.Alias.Signature.OID.1.2.840.10045.4.3.1", "SHA224withECDSA");
        map.put("Alg.Alias.Signature.1.2.840.10045.4.3.1", "SHA224withECDSA");

        map.put("Signature.SHA256withECDSA",
            "sun.security.ec.ECDSASignature$SHA256");
        map.put("Alg.Alias.Signature.OID.1.2.840.10045.4.3.2", "SHA256withECDSA");
        map.put("Alg.Alias.Signature.1.2.840.10045.4.3.2", "SHA256withECDSA");

        map.put("Signature.SHA384withECDSA",
            "sun.security.ec.ECDSASignature$SHA384");
        map.put("Alg.Alias.Signature.OID.1.2.840.10045.4.3.3", "SHA384withECDSA");
        map.put("Alg.Alias.Signature.1.2.840.10045.4.3.3", "SHA384withECDSA");

        map.put("Signature.SHA512withECDSA",
            "sun.security.ec.ECDSASignature$SHA512");
        map.put("Alg.Alias.Signature.OID.1.2.840.10045.4.3.4", "SHA512withECDSA");
        map.put("Alg.Alias.Signature.1.2.840.10045.4.3.4", "SHA512withECDSA");

        String ecKeyClasses = "java.security.interfaces.ECPublicKey" +
                "|java.security.interfaces.ECPrivateKey";
        map.put("Signature.NONEwithECDSA SupportedKeyClasses", ecKeyClasses);
        map.put("Signature.SHA1withECDSA SupportedKeyClasses", ecKeyClasses);
        map.put("Signature.SHA224withECDSA SupportedKeyClasses", ecKeyClasses);
        map.put("Signature.SHA256withECDSA SupportedKeyClasses", ecKeyClasses);
        map.put("Signature.SHA384withECDSA SupportedKeyClasses", ecKeyClasses);
        map.put("Signature.SHA512withECDSA SupportedKeyClasses", ecKeyClasses);

        map.put("Signature.SHA1withECDSA KeySize", "256");

        map.put("Signature.NONEwithECDSA ImplementedIn", "Software");
        map.put("Signature.SHA1withECDSA ImplementedIn", "Software");
        map.put("Signature.SHA224withECDSA ImplementedIn", "Software");
        map.put("Signature.SHA256withECDSA ImplementedIn", "Software");
        map.put("Signature.SHA384withECDSA ImplementedIn", "Software");
        map.put("Signature.SHA512withECDSA ImplementedIn", "Software");

        /*
         *  Key Pair Generator engine
         */
        map.put("KeyPairGenerator.EC", "sun.security.ec.ECKeyPairGenerator");
        map.put("Alg.Alias.KeyPairGenerator.EllipticCurve", "EC");

        map.put("KeyPairGenerator.EC KeySize", "256");

        map.put("KeyPairGenerator.EC ImplementedIn", "Software");

        /*
         * Key Agreement engine
         */
        map.put("KeyAgreement.ECDH", "sun.security.ec.ECDHKeyAgreement");

        map.put("KeyAgreement.ECDH SupportedKeyClasses", ecKeyClasses);

        map.put("KeyAgreement.ECDH ImplementedIn", "Software");
    }
}
