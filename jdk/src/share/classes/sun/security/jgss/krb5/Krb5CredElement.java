package sun.security.jgss.krb5;

import org.ietf.jgss.*;
import sun.security.jgss.spi.*;
import sun.security.krb5.*;
import java.security.Provider;

/**
 * Provides type safety for Krb5 credential elements.
 */
interface Krb5CredElement
    extends GSSCredentialSpi {
}
