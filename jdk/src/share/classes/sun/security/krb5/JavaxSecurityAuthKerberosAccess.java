package sun.security.krb5;

import javax.security.auth.kerberos.KeyTab;
import sun.security.krb5.EncryptionKey;
import sun.security.krb5.PrincipalName;

/**
 * An unsafe tunnel to get non-public access to classes in the
 * javax.security.auth.kerberos package.
 */
public interface JavaxSecurityAuthKerberosAccess {
    /**
     * Returns a snapshot to the backing keytab
     */
    public sun.security.krb5.internal.ktab.KeyTab keyTabTakeSnapshot(
            KeyTab ktab);
}
