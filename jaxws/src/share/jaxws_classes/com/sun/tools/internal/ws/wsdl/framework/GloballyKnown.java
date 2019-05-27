package com.sun.tools.internal.ws.wsdl.framework;

/**
 * An interface implemented by entities which can be defined in a target namespace.
 */
public interface GloballyKnown extends Elemental {
    public String getName();
    public Kind getKind();
    public Defining getDefining();
}
