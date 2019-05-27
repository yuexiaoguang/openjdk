package com.sun.tools.internal.jxc;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Message resources.
 */
enum Messages {
    // Accessor
    UNEXPECTED_NGCC_TOKEN, // 3 args
    BASEDIR_DOESNT_EXIST, // 1 arg
    USAGE, //0 args
    FULLVERSION, // 0 args
    VERSION, // 0 args
    ;

    private static final ResourceBundle rb = ResourceBundle.getBundle(Messages.class.getPackage().getName() +".MessageBundle");

    @Override
    public String toString() {
        return format();
    }

    public String format( Object... args ) {
        return MessageFormat.format( rb.getString(name()), args );
    }
}
