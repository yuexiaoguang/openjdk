package sun.security.krb5.internal.ccache;

import sun.security.krb5.*;
import sun.security.krb5.internal.*;
import java.util.StringTokenizer;
import java.util.Vector;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * CredentialsCache stores credentials(tickets, session keys, etc) in a semi-permanent store
 * for later use by different program.
 */
public abstract class CredentialsCache {
    static CredentialsCache singleton = null;
    static String cacheName;
    private static boolean DEBUG = Krb5.DEBUG;

    public static CredentialsCache getInstance(PrincipalName principal) {
        return FileCredentialsCache.acquireInstance(principal, null);
    }

    public static CredentialsCache getInstance(String cache) {
        if ((cache.length() >= 5) && cache.substring(0, 5).equalsIgnoreCase("FILE:")) {
            return FileCredentialsCache.acquireInstance(null, cache.substring(5));
        }
        // XXX else, memory credential cache
        // default is file credential cache.
        return FileCredentialsCache.acquireInstance(null, cache);
    }

    public static CredentialsCache getInstance(PrincipalName principal,
                                               String cache) {

        // XXX Modify this to use URL framework of the JDK
        if (cache != null &&
            (cache.length() >= 5) &&
            cache.regionMatches(true, 0, "FILE:", 0, 5)) {
            return FileCredentialsCache.acquireInstance(principal,
                                                        cache.substring(5));
        }

        // When cache is null, read the default cache.
        // XXX else ..we haven't provided support for memory credential cache
        // yet. (supported in native code)
        // default is file credentials cache.
        return FileCredentialsCache.acquireInstance(principal, cache);

    }

    /**
     * Gets the default credentials cache.
     */
    public static CredentialsCache getInstance() {
        // Default credentials cache is file-based.
        return FileCredentialsCache.acquireInstance();
    }

    public static CredentialsCache create(PrincipalName principal, String name) {
        if (name == null) {
            throw new RuntimeException("cache name error");
        }
        if ((name.length() >= 5)
            && name.regionMatches(true, 0, "FILE:", 0, 5)) {
            name = name.substring(5);
            return (FileCredentialsCache.New(principal, name));
        }
        // else return file credentials cache
        // default is file credentials cache.
        return (FileCredentialsCache.New(principal, name));
    }

    public static CredentialsCache create(PrincipalName principal) {
        // create a default credentials cache for a specified principal
        return (FileCredentialsCache.New(principal));
    }

    public static String cacheName() {
        return cacheName;
    }

    public abstract PrincipalName getPrimaryPrincipal();
    public abstract void update(Credentials c);
    public abstract void save() throws IOException, KrbException;
    public abstract Credentials[] getCredsList();
    public abstract Credentials getDefaultCreds();
    public abstract Credentials getCreds(PrincipalName sname);
    public abstract Credentials getCreds(LoginOptions options, PrincipalName sname);
}
