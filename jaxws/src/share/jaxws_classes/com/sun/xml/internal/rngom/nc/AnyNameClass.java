package com.sun.xml.internal.rngom.nc;

import javax.xml.namespace.QName;

final class AnyNameClass extends NameClass {

    protected AnyNameClass() {} // no instanciation

    public boolean contains(QName name) {
        return true;
    }

    public int containsSpecificity(QName name) {
        return SPECIFICITY_ANY_NAME;
    }

    @Override
    public boolean equals(Object obj) {
        return obj==this;
    }

    @Override
    public int hashCode() {
        return AnyNameClass.class.hashCode();
    }

    public <V> V accept(NameClassVisitor<V> visitor) {
        return visitor.visitAnyName();
    }

    public boolean isOpen() {
        return true;
    }

}
