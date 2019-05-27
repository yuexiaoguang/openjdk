package com.sun.xml.internal.bind;

import java.util.logging.Logger;

public final class Util {
    private Util() {}   // no instanciation

    /**
     * Gets the logger for the caller's class.
     *
     * @since 2.0
     */
    public static Logger getClassLogger() {
        try {
            StackTraceElement[] trace = new Exception().getStackTrace();
            return Logger.getLogger(trace[1].getClassName());
        } catch( SecurityException e) {
            return Logger.getLogger("com.sun.xml.internal.bind"); // use the default
        }
    }

    /**
     * Reads the system property value and takes care of {@link SecurityException}.
     */
    public static String getSystemProperty(String name) {
        try {
            return System.getProperty(name);
        } catch( SecurityException e ) {
            return null;
        }
    }
}
