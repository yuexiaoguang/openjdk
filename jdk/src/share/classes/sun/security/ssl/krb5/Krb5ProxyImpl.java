package sun.security.ssl.krb5;

import java.security.AccessControlContext;
import java.security.Permission;
import java.security.Principal;
import java.util.Set;
import javax.crypto.SecretKey;
import javax.security.auth.Subject;
import javax.security.auth.kerberos.KerberosKey;
import javax.security.auth.kerberos.KeyTab;
import javax.security.auth.kerberos.ServicePermission;
import javax.security.auth.login.LoginException;

import sun.security.jgss.GSSCaller;
import sun.security.jgss.krb5.Krb5Util;
import sun.security.jgss.krb5.ServiceCreds;
import sun.security.krb5.PrincipalName;
import sun.security.ssl.Krb5Proxy;

/**
 * An implementation of Krb5Proxy that simply delegates to the appropriate
 * Kerberos APIs.
 */
public class Krb5ProxyImpl implements Krb5Proxy {

    public Krb5ProxyImpl() { }

    @Override
    public Subject getClientSubject(AccessControlContext acc)
            throws LoginException {
        return Krb5Util.getSubject(GSSCaller.CALLER_SSL_CLIENT, acc);
    }

    @Override
    public Subject getServerSubject(AccessControlContext acc)
            throws LoginException {
        return Krb5Util.getSubject(GSSCaller.CALLER_SSL_SERVER, acc);
    }

    @Override
    public Object getServiceCreds(AccessControlContext acc)
            throws LoginException {
        ServiceCreds serviceCreds =
            Krb5Util.getServiceCreds(GSSCaller.CALLER_SSL_SERVER, null, acc);
        return serviceCreds;
    }

    @Override
    public String getServerPrincipalName(Object serviceCreds) {
        return ((ServiceCreds)serviceCreds).getName();
    }

    @Override
    public String getPrincipalHostName(Principal principal) {
        if (principal == null) {
           return null;
        }
        String hostName = null;
        try {
            PrincipalName princName =
                new PrincipalName(principal.getName(),
                        PrincipalName.KRB_NT_SRV_HST);
            String[] nameParts = princName.getNameStrings();
            if (nameParts.length >= 2) {
                hostName = nameParts[1];
            }
        } catch (Exception e) {
            // ignore
        }
        return hostName;
    }


    @Override
    public Permission getServicePermission(String principalName,
            String action) {
        return new ServicePermission(principalName, action);
    }

    @Override
    public boolean isRelated(Subject subject, Principal princ) {
        if (princ == null) return false;
        Set<Principal> principals =
                subject.getPrincipals(Principal.class);
        if (principals.contains(princ)) {
            // bound to this principal
            return true;
        }
        for (KeyTab pc: subject.getPrivateCredentials(KeyTab.class)) {
            if (!pc.isBound()) {
                return true;
            }
        }
        return false;
    }
}
