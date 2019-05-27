package jdk.nashorn.internal.ir;

import jdk.nashorn.internal.ir.visitor.NodeVisitor;

abstract class LexicalContextStatement extends Statement implements LexicalContextNode {
    /**
     * Constructor
     *
     * @param lineNumber line number
     * @param token      token
     * @param finish     finish
     */
    protected LexicalContextStatement(final int lineNumber, final long token, final int finish) {
        super(lineNumber, token, finish);
    }

    /**
     * Copy constructor
     *
     * @param node source node
     */
    protected LexicalContextStatement(final LexicalContextStatement node) {
        super(node);
    }

    @Override
    public Node accept(final NodeVisitor<? extends LexicalContext> visitor) {
        return Acceptor.accept(this, visitor);
    }
}
