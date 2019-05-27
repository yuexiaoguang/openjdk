package sun.security.krb5.internal.rcache;

import java.util.Objects;

/**
 * The class represents a new style replay cache entry. It can be either used
 * inside memory or in a dfl file.
 */
public class AuthTimeWithHash extends AuthTime
        implements Comparable<AuthTimeWithHash> {

    final String hash;

    /**
     * Constructs a new <code>AuthTimeWithHash</code>.
     */
    public AuthTimeWithHash(String client, String server,
            int ctime, int cusec, String hash) {
        super(client, server, ctime, cusec);
        this.hash = hash;
    }

    /**
     * Compares if an object equals to an <code>AuthTimeWithHash</code> object.
     * @param o an object.
     * @return true if two objects are equivalent, otherwise, return false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthTimeWithHash)) return false;
        AuthTimeWithHash that = (AuthTimeWithHash)o;
        return Objects.equals(hash, that.hash)
                && Objects.equals(client, that.client)
                && Objects.equals(server, that.server)
                && ctime == that.ctime
                && cusec == that.cusec;
    }

    /**
     * Returns a hash code for this <code>AuthTimeWithHash</code> object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(hash);
    }

    @Override
    public String toString() {
        return String.format("%d/%06d/%s/%s", ctime, cusec, hash, client);
    }

    @Override
    public int compareTo(AuthTimeWithHash other) {
        int cmp = 0;
        if (ctime != other.ctime) {
            cmp = Integer.compare(ctime, other.ctime);
        } else if (cusec != other.cusec) {
            cmp = Integer.compare(cusec, other.cusec);
        } else {
            cmp = hash.compareTo(other.hash);
        }
        return cmp;
    }

    /**
     * Compares with a possibly old style object. Used
     * in DflCache$Storage#loadAndCheck.
     * @return true if all AuthTime fields are the same
     */
    public boolean isSameIgnoresHash(AuthTime old) {
        return  client.equals(old.client) &&
                server.equals(old.server) &&
                ctime == old.ctime &&
                cusec == old.cusec;
    }

    // Methods used when saved in a dfl file. See DflCache.java

    /**
     * Encodes to be used in a dfl file
     * @param withHash write new style if true
     */
    @Override
    public byte[] encode(boolean withHash) {
        String cstring;
        String sstring;
        if (withHash) {
            cstring = "";
            sstring = String.format("HASH:%s %d:%s %d:%s", hash,
                    client.length(), client,
                    server.length(), server);
        } else {
            cstring = client;
            sstring = server;
        }
        return encode0(cstring, sstring);
    }
}
