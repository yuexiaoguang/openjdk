package com.sun.tools.javac.main;

import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Log.PrefixKind;
import java.io.File;

/**
 * Helper object to be used by {@link Option#process}, providing access to
 * the compilation environment.
 *
 * <p><b>This is NOT part of any supported API.
 * If you write code that depends on this, you do so at your own
 * risk.  This code and its internal interfaces are subject to change
 * or deletion without notice.</b></p>
 */
public abstract class OptionHelper {

    /** Get the current value of an option. */
    public abstract String get(Option option);

    /** Set the value of an option. */
    public abstract void put(String name, String value);

    /** Remove any prior value for an option. */
    public abstract void remove(String name);

    /** Get access to the Log for the compilation. */
    public abstract Log getLog();

    /** Get the name of the tool, such as "javac", to be used in info like -help. */
    public abstract String getOwnName();

    /** Report an error. */
    abstract void error(String key, Object... args);

    /** Record a file to be compiled. */
    abstract void addFile(File f);

    /** Record the name of a class for annotation processing. */
    abstract void addClassName(String s);

    /** An implementation of OptionHelper that mostly throws exceptions. */
    public static class GrumpyHelper extends OptionHelper {
        private final Log log;

        public GrumpyHelper(Log log) {
            this.log = log;
        }

        @Override
        public Log getLog() {
            return log;
        }

        @Override
        public String getOwnName() {
            throw new IllegalStateException();
        }

        @Override
        public String get(Option option) {
            throw new IllegalArgumentException();
        }

        @Override
        public void put(String name, String value) {
            throw new IllegalArgumentException();
        }

        @Override
        public void remove(String name) {
            throw new IllegalArgumentException();
        }

        @Override
        void error(String key, Object... args) {
            throw new IllegalArgumentException(log.localize(PrefixKind.JAVAC, key, args));
        }

        @Override
        public void addFile(File f) {
            throw new IllegalArgumentException(f.getPath());
        }

        @Override
        public void addClassName(String s) {
            throw new IllegalArgumentException(s);
        }
    }

}
