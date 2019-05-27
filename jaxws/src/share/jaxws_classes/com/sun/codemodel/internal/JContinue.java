package com.sun.codemodel.internal;

/**
 * JContinue statement
 */
class JContinue implements JStatement {

    private final JLabel label;

    /**
     * JContinue constructor.
     *
     * @param _label
     *      a valid label or null.
     */
    JContinue(JLabel _label) {
        this.label = _label;
    }

    public void state(JFormatter f) {
        if( label==null )
            f.p("continue;").nl();
        else
            f.p("continue").p(label.label).p(';').nl();
    }

}
