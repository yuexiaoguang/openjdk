package com.sun.jdi;

/**
 * Thrown to indicate that the requested operation cannot be
 * completed because the specified class has not yet been prepared.
 */
@jdk.Exported
public class ClassNotPreparedException extends RuntimeException {
    private static final long serialVersionUID = -6120698967144079642L;
    public ClassNotPreparedException()
    {
        super();
    }

    public ClassNotPreparedException(String s)
    {
        super(s);
    }
}
