package com.sun.codemodel.internal;

/**
 * String literal.
 */
public class JStringLiteral extends JExpressionImpl {

    public final String str;


    JStringLiteral(String what) {
        this.str = what;

    }


    public void generate(JFormatter f) {
        f.p(JExpr.quotify('"', str));
    }
}
