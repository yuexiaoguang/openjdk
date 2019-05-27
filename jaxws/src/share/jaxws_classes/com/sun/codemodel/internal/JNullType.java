package com.sun.codemodel.internal;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Special class object that represents the type of "null".
 *
 * <p>
 * Use this class with care.
 */
public final class JNullType extends JClass {

    JNullType(JCodeModel _owner) {
        super(_owner);
    }

    public String name() { return "null"; }
    public String fullName() { return "null"; }

    public JPackage _package() { return owner()._package(""); }

    public JClass _extends() { return null; }

    public Iterator<JClass> _implements() {
        return Collections.<JClass>emptyList().iterator();
    }

    public boolean isInterface() { return false; }
    public boolean isAbstract() { return false; }

    protected JClass substituteParams(JTypeVar[] variables, List<JClass> bindings) {
        return this;
    }
}
