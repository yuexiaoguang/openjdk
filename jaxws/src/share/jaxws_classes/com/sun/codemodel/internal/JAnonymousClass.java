package com.sun.codemodel.internal;

/**
 * Anonymous class quick hack.
 */
class JAnonymousClass extends JDefinedClass {

    /**
     * Base interface/class from which this anonymous class is built.
     */
    private final JClass base;

    JAnonymousClass( JClass _base) {
        super(_base.owner(), 0, null);
        this.base = _base;
    }

    @Override
    public String fullName() {
        return base.fullName();
    }

    @Override
    public void generate(JFormatter f) {
        f.t(base);
    }

}
