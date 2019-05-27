package sun.security.krb5.internal.rcache;

import java.util.*;
import sun.security.krb5.internal.KerberosTime;
import sun.security.krb5.internal.KrbApErrException;
import sun.security.krb5.internal.ReplayCache;

/**
 * This class stores replay caches. AuthTimeWithHash objects are categorized
 * into AuthLists keyed by the names of client and server.
 */
public class MemoryCache extends ReplayCache {

    // TODO: One day we'll need to read dynamic krb5.conf.
    private static final int lifespan = KerberosTime.getDefaultSkew();
    private static final boolean DEBUG = sun.security.krb5.internal.Krb5.DEBUG;

    private final Map<String,AuthList> content = new HashMap<>();

    @Override
    public synchronized void checkAndStore(KerberosTime currTime, AuthTimeWithHash time)
            throws KrbApErrException {
        String key = time.client + "|" + time.server;
        AuthList rc = content.get(key);
        if (DEBUG) {
            System.out.println("MemoryCache: add " + time + " to " + key);
        }
        if (rc == null) {
            rc = new AuthList(lifespan);
            rc.put(time, currTime);
            if (!rc.isEmpty()) {
                content.put(key, rc);
            }
        } else {
            if (DEBUG) {
                System.out.println("MemoryCache: Existing AuthList:\n" + rc);
            }
            rc.put(time, currTime);
            if (rc.isEmpty()) {
                content.remove(key);
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (AuthList rc: content.values()) {
            sb.append(rc.toString());
        }
        return sb.toString();
    }
}
