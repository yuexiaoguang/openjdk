package com.sun.xml.internal.xsom.util;

import com.sun.xml.internal.xsom.XSType;

/**
 * Perform a transitive closure operation on a type to determine if it
 * belongs to this set.
 *
 * The contains method returns true if the TypeSet contains an instance
 * of the specified XSType or any of the base types of the XSType.
 */
public class TypeClosure extends TypeSet {

    private final TypeSet typeSet;

    public TypeClosure(TypeSet typeSet) {
        this.typeSet = typeSet;
    }

    /* (non-Javadoc)
     * @see com.sun.xml.internal.xsom.util.TypeSet#contains(com.sun.xml.internal.xsom.XSDeclaration)
     *
     * transitive closure variation on the contains method.
     */
    public boolean contains(XSType type) {
        if( typeSet.contains(type) ) {
            return true;
        } else {
            XSType baseType = type.getBaseType();
            if( baseType == null ) {
                return false;
            } else {
                // climb the super type hierarchy
                return contains(baseType);
            }
        }
    }

}
