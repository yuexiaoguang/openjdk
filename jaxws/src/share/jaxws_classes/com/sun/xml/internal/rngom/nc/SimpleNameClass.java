package com.sun.xml.internal.rngom.nc;

import javax.xml.namespace.QName;

public class SimpleNameClass extends NameClass {

    public final QName name;

    public SimpleNameClass(QName name) {
        this.name = name;
    }

    public SimpleNameClass(String nsUri, String localPart) {
        this(new QName(nsUri, localPart));
    }

    public SimpleNameClass(String nsUri, String localPart, String prefix) {
        this(new QName(nsUri, localPart, prefix));
    }

    @Override
    public boolean contains(QName name) {
        return this.name.equals(name);
    }

    @Override
    public int containsSpecificity(QName name) {
        return contains(name) ? SPECIFICITY_NAME : SPECIFICITY_NONE;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof SimpleNameClass)) {
            return false;
        }
        SimpleNameClass other = (SimpleNameClass) obj;
        return name.equals(other.name);
    }

    @Override
    public <V> V accept(NameClassVisitor<V> visitor) {
        return visitor.visitName(name);
    }

    @Override
    public boolean isOpen() {
        return false;
    }
}
