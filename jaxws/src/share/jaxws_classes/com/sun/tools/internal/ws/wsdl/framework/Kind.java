package com.sun.tools.internal.ws.wsdl.framework;

/**
 * A kind of entity.
 */
public final class Kind {

    public Kind(String s) {
        _name = s;
    }

    public String getName() {
        return _name;
    }

    private String _name;
}
