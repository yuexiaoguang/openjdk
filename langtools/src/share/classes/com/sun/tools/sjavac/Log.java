package com.sun.tools.sjavac;

import java.io.PrintStream;

/**
 * Utility class only for sjavac logging.
 * The log level can be set using for example --log=DEBUG on the sjavac command line.
 *
 * <p><b>This is NOT part of any supported API.
 * If you write code that depends on this, you do so at your own
 * risk.  This code and its internal interfaces are subject to change
 * or deletion without notice.</b></p>
 */
public class Log {
    private static PrintStream out, err;

    public final static int WARN = 1;
    public final static int INFO = 2;
    public final static int DEBUG = 3;
    public final static int TRACE = 4;
    private static int level = WARN;

    static public void trace(String msg) {
        if (level >= TRACE) {
            out.println(msg);
        }
    }

    static public void debug(String msg) {
        if (level >= DEBUG) {
            out.println(msg);
        }
    }

    static public void info(String msg) {
        if (level >= INFO) {
            out.println(msg);
        }
    }

    static public void warn(String msg) {
        err.println(msg);
    }

    static public void error(String msg) {
        err.println(msg);
    }

    static public void setLogLevel(String l, PrintStream o, PrintStream e)
        throws ProblemException {
        out = o;
        err = e;
        if (l.equals("warn")) level = WARN;
        else if (l.equals("info")) level = INFO;
        else if (l.equals("debug")) level = DEBUG;
        else if (l.equals("trace")) level = TRACE;
        else throw new ProblemException("No such log level \""+l+"\"");
    }

    static public boolean isTracing() {
        return level >= TRACE;
    }

    static public boolean isDebugging() {
        return level >= DEBUG;
    }
}
