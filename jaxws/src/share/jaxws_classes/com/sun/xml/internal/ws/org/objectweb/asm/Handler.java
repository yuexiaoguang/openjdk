package com.sun.xml.internal.ws.org.objectweb.asm;

/**
 * Information about an exception handler block.
 */
class Handler {

    /**
     * Beginning of the exception handler's scope (inclusive).
     */
    Label start;

    /**
     * End of the exception handler's scope (exclusive).
     */
    Label end;

    /**
     * Beginning of the exception handler's code.
     */
    Label handler;

    /**
     * Internal name of the type of exceptions handled by this handler, or
     * <tt>null</tt> to catch any exceptions.
     */
    String desc;

    /**
     * Constant pool index of the internal name of the type of exceptions
     * handled by this handler, or 0 to catch any exceptions.
     */
    int type;

    /**
     * Next exception handler block info.
     */
    Handler next;
}
