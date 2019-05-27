package com.sun.tools.internal.ws.wscompile;

import com.sun.istack.internal.Nullable;

public class BadCommandLineException extends Exception {
    private transient Options options;

    public BadCommandLineException(String msg) {
        super(msg);
    }

    public BadCommandLineException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadCommandLineException() {
        this(null);
    }

    public void initOptions(Options opt) {
        assert this.options==null;
        this.options = opt;
    }

    /**
     * Gets the partly parsed option object, if any.
     */
    public @Nullable
    Options getOptions() {
        return options;
    }
}
