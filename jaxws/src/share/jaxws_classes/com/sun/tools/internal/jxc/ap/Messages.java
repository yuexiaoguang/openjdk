package com.sun.tools.internal.jxc.ap;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Message resources.
 */
enum Messages {
    // Accessor
    NON_EXISTENT_FILE, // 1 arg
    UNRECOGNIZED_PARAMETER, //1 arg
    OPERAND_MISSING, // 1 arg
    ;

    private static final ResourceBundle rb = ResourceBundle.getBundle(Messages.class.getPackage().getName() +".MessageBundle");

    public String toString() {
        return format();
    }

    public String format( Object... args ) {
        return MessageFormat.format( rb.getString(name()), args );
    }
}
