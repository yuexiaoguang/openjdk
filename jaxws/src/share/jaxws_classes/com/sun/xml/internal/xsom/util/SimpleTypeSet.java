package com.sun.xml.internal.xsom.util;

import java.util.Set;

import com.sun.xml.internal.xsom.XSType;

/**
 * A very simple TypeSet.
 *
 * The contains method returns true if the set explicitly contains an
 * instance of the specified XSType.
 */
public class SimpleTypeSet extends TypeSet {

    private final Set typeSet;

    public SimpleTypeSet(Set s) {
        typeSet = s;
    }

    /* (non-Javadoc)
     * @see com.sun.xml.internal.xsom.util.TypeSet#contains(com.sun.xml.internal.xsom.XSDeclaration)
     */
    public boolean contains(XSType type) {
        return typeSet.contains(type);
    }

}
