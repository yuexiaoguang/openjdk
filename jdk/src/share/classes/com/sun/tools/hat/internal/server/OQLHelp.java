package com.sun.tools.hat.internal.server;

import java.io.*;

/**
 * This handles Object Query Language (OQL) help.
 */
class OQLHelp extends QueryHandler {

    public OQLHelp() {
    }

    public void run() {
        InputStream is = getClass().getResourceAsStream("/com/sun/tools/hat/resources/oqlhelp.html");
        int ch = -1;
        try {
            is = new BufferedInputStream(is);
            while ( (ch = is.read()) != -1) {
                out.print((char)ch);
            }
        } catch (Exception exp) {
            printException(exp);
        }
    }
}
