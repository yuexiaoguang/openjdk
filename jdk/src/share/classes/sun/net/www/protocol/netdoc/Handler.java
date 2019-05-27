package sun.net.www.protocol.netdoc;

import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;
import java.net.URLStreamHandler;
import java.io.InputStream;
import java.io.IOException;

public class Handler extends URLStreamHandler {
    static URL base;

    /*
     * Attempt to find a load the given url using the default (network)
     * documentation location.  If that fails, use the local copy
     */
    public synchronized URLConnection openConnection(URL u)
        throws IOException
    {
        URLConnection uc = null;
        URL ru;

        Boolean tmp = java.security.AccessController.doPrivileged(
            new sun.security.action.GetBooleanAction("newdoc.localonly"));
        boolean localonly = tmp.booleanValue();

        String docurl = java.security.AccessController.doPrivileged(
            new sun.security.action.GetPropertyAction("doc.url"));

        String file = u.getFile();
        if (!localonly) {
            try {
                if (base == null) {
                    base = new URL(docurl);
                }
                ru = new URL(base, file);
            } catch (MalformedURLException e) {
                ru = null;
            }
            if (ru != null) {
                uc = ru.openConnection();
            }
        }

        if (uc == null) {
            try {
                ru = new URL("file", "~", file);

                uc = ru.openConnection();
                InputStream is = uc.getInputStream();   // Check for success.
            } catch (MalformedURLException e) {
                uc = null;
            } catch (IOException e) {
                uc = null;
            }
        }

        if (uc == null) {
            throw new IOException("Can't find file for URL: "
                                  +u.toExternalForm());
        }
        return uc;
    }
}
