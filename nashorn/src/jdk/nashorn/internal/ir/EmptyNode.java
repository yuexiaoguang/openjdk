package jdk.nashorn.internal.ir;

import jdk.nashorn.internal.ir.annotations.Immutable;
import jdk.nashorn.internal.ir.visitor.NodeVisitor;

/**
 * IR representation for an empty statement.
 */
@Immutable
public final class EmptyNode extends Statement {

    /**
     * Constructor
     *
     * @param node node to wrap
     */
    public EmptyNode(final Statement node) {
        super(node);
    }

    /**
     * Constructor
     *
     * @param lineNumber line number
     * @param token      token
     * @param finish     finish
     */
    public EmptyNode(final int lineNumber, final long token, final int finish) {
        super(lineNumber, token, finish);
    }


    @Override
    public Node accept(final NodeVisitor<? extends LexicalContext> visitor) {
        if (visitor.enterEmptyNode(this)) {
            return visitor.leaveEmptyNode(this);
        }
        return this;
    }

    @Override
    public void toString(final StringBuilder sb) {
        sb.append(';');
    }

}
