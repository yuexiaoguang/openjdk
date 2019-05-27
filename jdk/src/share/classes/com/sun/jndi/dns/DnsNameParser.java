package com.sun.jndi.dns;

import javax.naming.*;


/**
 * A name parser for DNS names.
 */
class DnsNameParser implements NameParser {

    public Name parse(String name) throws NamingException {
        return new DnsName(name);
    }


    // Every DnsNameParser is created equal.

    public boolean equals(Object obj) {
        return (obj instanceof DnsNameParser);
    }

    public int hashCode() {
        return DnsNameParser.class.hashCode() + 1;
    }
}
