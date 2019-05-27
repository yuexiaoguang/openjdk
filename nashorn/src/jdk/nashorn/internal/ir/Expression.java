package jdk.nashorn.internal.ir;

import jdk.nashorn.internal.codegen.types.Type;

/**
 * Common superclass for all expression nodes. Expression nodes can have
 * an associated symbol as well as a type.
 */
public abstract class Expression extends Node {
    private Symbol symbol;

    Expression(long token, int start, int finish) {
        super(token, start, finish);
    }

    Expression(long token, int finish) {
        super(token, finish);
    }

    Expression(Expression expr) {
        super(expr);
        this.symbol = expr.symbol;
    }

    /**
     * Return the Symbol the compiler has assigned to this Node. The symbol
     * is the place where it's expression value is stored after evaluation
     *
     * @return the symbol
     */
    public Symbol getSymbol() {
        return symbol;
    }

    /**
     * Assign a symbol to this node. See {@link Expression#getSymbol()} for explanation
     * of what a symbol is
     *
     * @param lc lexical context
     * @param symbol the symbol
     * @return new node
     */
    public Expression setSymbol(final LexicalContext lc, final Symbol symbol) {
        if (this.symbol == symbol) {
            return this;
        }
        final Expression newExpr = (Expression)clone();
        newExpr.symbol = symbol;
        return newExpr;
    }

    /**
     * Check if the expression has a type. The default behavior is to go into the symbol
     * and check the symbol type, but there may be overrides, for example in
     * getters that require a different type than the internal representation
     *
     * @return true if a type exists
     */
    public boolean hasType() {
        return getSymbol() != null;
    }

    /**
     * Returns the type of the expression. Typically this is the symbol type. No types
     * are stored in the expression itself, unless it implements TypeOverride.
     *
     * @return the type of the node.
     */
    public Type getType() {
        assert hasType() : this + " has no type";
        return symbol.getSymbolType();
    }

    /**
     * Returns {@code true} if this expression depends exclusively on state that is constant
     * or local to the currently running function and thus inaccessible to other functions.
     * This implies that a local expression must not call any other functions (neither directly
     * nor implicitly through a getter, setter, or object-to-primitive type conversion).
     *
     * @return true if this expression does not depend on state shared with other functions.
     */
    public boolean isLocal() {
        return false;
    }
}
