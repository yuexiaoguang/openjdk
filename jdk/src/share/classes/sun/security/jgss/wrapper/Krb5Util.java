package sun.security.jgss.wrapper;

import org.ietf.jgss.*;
import javax.security.auth.kerberos.ServicePermission;

/**
 * This class is an utility class for Kerberos related stuff.
 */
class Krb5Util {

    // Return the Kerberos TGS principal name using the domain
    // of the specified <code>name</code>
    static String getTGSName(GSSNameElement name)
        throws GSSException {
        String krbPrinc = name.getKrbName();
        int atIndex = krbPrinc.indexOf("@");
        String realm = krbPrinc.substring(atIndex + 1);
        StringBuffer buf = new StringBuffer("krbtgt/");
        buf.append(realm).append('@').append(realm);
        return buf.toString();
    }

    // Perform the Service Permission check using the specified
    // <code>target</code> and <code>action</code>
    static void checkServicePermission(String target, String action) {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            SunNativeProvider.debug("Checking ServicePermission(" +
                                    target + ", " + action + ")");
            ServicePermission perm =
                new ServicePermission(target, action);
            sm.checkPermission(perm);
        }
    }
}
