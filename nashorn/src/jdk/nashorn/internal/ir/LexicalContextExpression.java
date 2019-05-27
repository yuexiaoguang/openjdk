package jdk.nashorn.internal.ir;

import jdk.nashorn.internal.ir.visitor.NodeVisitor;

abstract class LexicalContextExpression extends Expression implements LexicalContextNode {

    LexicalContextExpression(LexicalContextExpression expr) {
        super(expr);
    }

    LexicalContextExpression(long token, int start, int finish) {
        super(token, start, finish);
    }

    LexicalContextExpression(long token, int finish) {
        super(token, finish);
    }

    @Override
    public Node accept(final NodeVisitor<? extends LexicalContext> visitor) {
        return Acceptor.accept(this, visitor);
    }

    /**
     * Set the symbol and replace in lexical context if applicable
     * @param lc     lexical context
     * @param symbol symbol
     * @return new node if symbol changed
     */
    @Override
    public Expression setSymbol(final LexicalContext lc, final Symbol symbol) {
        return Node.replaceInLexicalContext(lc, this, (LexicalContextExpression)super.setSymbol(null, symbol));
    }
}
