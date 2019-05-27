package com.sun.xml.internal.fastinfoset;

public class Notation {
    public final String name;
    public final String systemIdentifier;
    public final String publicIdentifier;

    /** Creates a new instance of Notation */
    public Notation(String _name, String _systemIdentifier, String _publicIdentifier) {
        name = _name;
        systemIdentifier = _systemIdentifier;
        publicIdentifier = _publicIdentifier;
    }

}
