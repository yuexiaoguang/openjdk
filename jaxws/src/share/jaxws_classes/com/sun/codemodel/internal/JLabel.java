package com.sun.codemodel.internal;

/**
 * Label that can be used for continue and break.
 */
public class JLabel implements JStatement {

    final String label;

    /**
     * JBreak constructor
     *
     * @param   _label
     *      break label or null.
     */
    JLabel( String _label ) {
        this.label = _label;
    }

    public void state(JFormatter f) {
        f.p(label+':').nl();
    }

}
