package jdk.nashorn.internal.ir;

import jdk.nashorn.internal.ir.annotations.Immutable;
import jdk.nashorn.internal.ir.visitor.NodeVisitor;

/**
 * IR representation for {@code break} statements.
 */
@Immutable
public final class BreakNode extends Statement {

    private final IdentNode label;

    /**
     * Constructor
     *
     * @param lineNumber line number
     * @param token      token
     * @param finish     finish
     * @param label      label for break or null if none
     */
    public BreakNode(final int lineNumber, final long token, final int finish, final IdentNode label) {
        super(lineNumber, token, finish);
        this.label = label;
    }

    @Override
    public boolean hasGoto() {
        return true;
    }

    /**
     * Assist in IR navigation.
     * @param visitor IR navigating visitor.
     */
    @Override
    public Node accept(final NodeVisitor<? extends LexicalContext> visitor) {
        if (visitor.enterBreakNode(this)) {
            return visitor.leaveBreakNode(this);
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
        sb.append("break");

        if (label != null) {
            sb.append(' ');
            label.toString(sb);
        }
    }
}
