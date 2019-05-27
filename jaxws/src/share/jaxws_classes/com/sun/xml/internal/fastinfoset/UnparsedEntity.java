package com.sun.xml.internal.fastinfoset;

public class UnparsedEntity extends Notation {
    public final String notationName;

    /** Creates a new instance of UnparsedEntityDeclaration */
    public UnparsedEntity(String _name, String _systemIdentifier, String _publicIdentifier, String _notationName) {
        super(_name, _systemIdentifier, _publicIdentifier);
        notationName = _notationName;
    }

}
