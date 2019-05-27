package com.sun.jdi;

/**
 * Thrown to indicate line number or variable information is not available.
 */
@jdk.Exported
public class AbsentInformationException extends Exception
{
    private static final long serialVersionUID = 4988939309582416373L;
    public AbsentInformationException()
    {
        super();
    }

    public AbsentInformationException(String s)
    {
        super(s);
    }
}
