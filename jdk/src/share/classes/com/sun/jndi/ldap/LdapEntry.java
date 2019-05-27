package com.sun.jndi.ldap;

import java.util.Vector;
import javax.naming.directory.Attributes;
import javax.naming.ldap.Control;

/**
  * A holder for an LDAP entry read from an LDAP server.
  */
final class LdapEntry {
    String DN;
    Attributes attributes;
    Vector<Control> respCtls = null;

    LdapEntry(String DN, Attributes attrs) {
        this.DN = DN;
        this.attributes = attrs;
    }

    LdapEntry(String DN, Attributes attrs, Vector<Control> respCtls) {
        this.DN = DN;
        this.attributes = attrs;
        this.respCtls = respCtls;
    }
}
