package com.sun.tools.internal.ws.wsdl.framework;

import org.xml.sax.Locator;
import com.sun.tools.internal.ws.wscompile.ErrorReceiver;

/**
 * An entity that can be defined in a target namespace.
 */
public abstract class GlobalEntity extends Entity implements GloballyKnown {

    public GlobalEntity(Defining defining, Locator locator, ErrorReceiver errorReceiver) {
        super(locator);
        _defining = defining;
        this.errorReceiver = errorReceiver;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public abstract Kind getKind();

    public Defining getDefining() {
        return _defining;
    }

    private Defining _defining;
    private String _name;
}
