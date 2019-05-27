/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */
package com.sun.tools.example.debug.bdi;

public class MethodNotFoundException extends Exception
{
    private static final long serialVersionUID = -2064968107599632609L;

    public MethodNotFoundException()
    {
        super();
    }

    public MethodNotFoundException(String s)
    {
        super(s);
    }
}