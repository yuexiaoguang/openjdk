package com.sun.security.jgss;

import org.ietf.jgss.*;

/**
 * The extended GSSCredential interface for supporting additional
 * functionalities not defined by {@code org.ietf.jgss.GSSCredential}.
 */
@jdk.Exported
public interface ExtendedGSSCredential extends GSSCredential {
    /**
     * Impersonates a principal. In Kerberos, this can be implemented
     * using the Microsoft S4U2self extension.
     * <p>
     * A {@link GSSException#NO_CRED GSSException.NO_CRED} will be thrown if the
     * impersonation fails. A {@link GSSException#FAILURE GSSException.FAILURE}
     * will be  thrown if the impersonation method is not available to this
     * credential object.
     * @param name the name of the principal to impersonate
     * @return a credential for that principal
     * @throws GSSException  containing the following
     * major error codes:
     *   {@link GSSException#NO_CRED GSSException.NO_CRED}
     *   {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public GSSCredential impersonate(GSSName name) throws GSSException;
}
