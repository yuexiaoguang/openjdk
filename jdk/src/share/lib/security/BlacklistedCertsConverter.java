import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Collection;

/**
 * This is the tool to convert blacklisted.certs.pem to blacklisted.certs.
 * Every time a new blacklisted certs is added, please append the PEM format
 * to the end of blacklisted.certs.pem (with proper comments) and then use
 * this tool to generate an updated blacklisted.certs. Make sure to include
 * changes to both in a changeset.
 */
public class BlacklistedCertsConverter {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Usage: java BlacklistedCertsConverter SHA-256" +
                    " < blacklisted.certs.pem > blacklisted.certs");
            System.exit(1);
        }
        String mdAlg = args[0];
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        Collection<? extends Certificate> certs
                = cf.generateCertificates(System.in);
        System.out.println("Algorithm=" + mdAlg);
        for (Certificate cert: certs) {
            System.out.println(
                    getCertificateFingerPrint(mdAlg, (X509Certificate)cert));
        }
    }

    /**
     * Converts a byte to hex digit and writes to the supplied buffer
     */
    private static void byte2hex(byte b, StringBuffer buf) {
        char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        int high = ((b & 0xf0) >> 4);
        int low = (b & 0x0f);
        buf.append(hexChars[high]);
        buf.append(hexChars[low]);
    }

    /**
     * Gets the requested finger print of the certificate.
     */
    private static String getCertificateFingerPrint(String mdAlg,
                                                    X509Certificate cert) {
        String fingerPrint = "";
        try {
            byte[] encCertInfo = cert.getEncoded();
            MessageDigest md = MessageDigest.getInstance(mdAlg);
            byte[] digest = md.digest(encCertInfo);
            StringBuffer buf = new StringBuffer();
            for (int i = 0; i < digest.length; i++) {
                byte2hex(digest[i], buf);
            }
            fingerPrint = buf.toString();
        } catch (NoSuchAlgorithmException | CertificateEncodingException e) {
            // ignored
        }
        return fingerPrint;
    }
}
