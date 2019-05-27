package jdk.nashorn.internal.ir;

import jdk.nashorn.internal.ir.annotations.Immutable;
import jdk.nashorn.internal.ir.visitor.NodeVisitor;

/**
 * IR representation for CONTINUE statements.
 */
@Immutable
public class ContinueNode extends Statement {

    private IdentNode label;

    /**
     * Constructor
     *
     * @param lineNumber line number
     * @param token      token
     * @param finish     finish
     * @param label      label for break or null if none
     */
    public ContinueNode(final int lineNumber, final long token, final int finish, final IdentNode label) {
        super(lineNumber, token, finish);
        this.label = label;
    }

    @Override
    public boolean hasGoto() {
        return true;
    }

    @Override
    public Node accept(final NodeVisitor<? extends LexicalContext> visitor) {
        if (visitor.enterContinueNode(this)) {
            return visitor.leaveContinueNode(this);
        }

        return this;
    }

    /**
     * Get the label for this break node
     * @return label, or null if none
     */
    public IdentNode getLabel() {
        return label;
    }

    @Override
    public void toString(final StringBuilder sb) {
        sb.append("continue");

        if (label != null) {
            sb.append(' ');
            label.toString(sb);
        }
    }
}

