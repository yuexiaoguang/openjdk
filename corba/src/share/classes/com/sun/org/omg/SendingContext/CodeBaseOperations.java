package com.sun.org.omg.SendingContext;


/**
* com/sun/org/omg/SendingContext/CodeBaseOperations.java
* Generated by the IDL-to-Java compiler (portable), version "3.0"
* from rt.idl
*/

// Edited to leave RunTime in org.omg.CORBA

public interface CodeBaseOperations  extends org.omg.SendingContext.RunTimeOperations
{

    // Operation to obtain the IR from the sending context
    com.sun.org.omg.CORBA.Repository get_ir ();

    // Operations to obtain a URL to the implementation code
    String implementation (String x);
    String[] implementations (String[] x);

    // the same information
    com.sun.org.omg.CORBA.ValueDefPackage.FullValueDescription meta (String x);
    com.sun.org.omg.CORBA.ValueDefPackage.FullValueDescription[] metas (String[] x);

    // information
    String[] bases (String x);
} // interface CodeBaseOperations
