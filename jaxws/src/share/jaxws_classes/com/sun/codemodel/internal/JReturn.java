package com.sun.codemodel.internal;


/**
 * A return statement
 */
class JReturn implements JStatement {

    /**
     * JExpression to return; may be null.
     */
    private JExpression expr;

    /**
     * JReturn constructor
     *
     * @param expr
     *        JExpression which evaluates to return value
     */
    JReturn(JExpression expr) {
       this.expr = expr;
    }

    public void state(JFormatter f) {
        f.p("return ");
        if (expr != null) f.g(expr);
        f.p(';').nl();
    }

}
