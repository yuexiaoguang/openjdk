package sun.security.jgss.krb5;

import org.ietf.jgss.*;
import sun.security.jgss.spi.*;
import java.util.Date;
import sun.security.krb5.internal.Ticket;

/**
 * Implements the krb5 proxy credential element used in constrained
 * delegation. It is used in both impersonation (where there is no Kerberos 5
 * communication between the middle server and the client) and normal
 * constrained delegation (where there is, but client has not called
 * requestCredDeleg(true)).
 */
public class Krb5ProxyCredential
    implements Krb5CredElement {

    public final Krb5InitCredential self;   // the middle server
    private final Krb5NameElement client;     // the client

    // The ticket with cname=client and sname=self. This can be a normal
    // service ticket or an S4U2self ticket.
    public final Ticket tkt;

    Krb5ProxyCredential(Krb5InitCredential self, Krb5NameElement client,
            Ticket tkt) {
        this.self = self;
        this.tkt = tkt;
        this.client = client;
    }

    // The client name behind the proxy
    @Override
    public final Krb5NameElement getName() throws GSSException {
        return client;
    }

    @Override
    public int getInitLifetime() throws GSSException {
        // endTime of tkt is not used by KDC, and it's also not
        // available in the case of kerberos constr deleg
        return self.getInitLifetime();
    }

    @Override
    public int getAcceptLifetime() throws GSSException {
        return 0;
    }

    @Override
    public boolean isInitiatorCredential() throws GSSException {
        return true;
    }

    @Override
    public boolean isAcceptorCredential() throws GSSException {
        return false;
    }

    @Override
    public final Oid getMechanism() {
        return Krb5MechFactory.GSS_KRB5_MECH_OID;
    }

    @Override
    public final java.security.Provider getProvider() {
        return Krb5MechFactory.PROVIDER;
    }

    @Override
    public void dispose() throws GSSException {
        try {
            self.destroy();
        } catch (javax.security.auth.DestroyFailedException e) {
            GSSException gssException =
                new GSSException(GSSException.FAILURE, -1,
                 "Could not destroy credentials - " + e.getMessage());
            gssException.initCause(e);
        }
    }

    @Override
    public GSSCredentialSpi impersonate(GSSNameSpi name) throws GSSException {
        // Cannot impersonate multiple levels without the impersonatee's TGT.
        throw new GSSException(GSSException.FAILURE, -1,
                "Only an initiate credentials can impersonate");
    }
}
