package jdk.nashorn.internal.ir;

import jdk.nashorn.internal.ir.annotations.Immutable;
import jdk.nashorn.internal.ir.visitor.NodeVisitor;

/**
 * IR representation for a labeled statement.
 */
@Immutable
public final class LabelNode extends LexicalContextStatement {
    /** Label ident. */
    private final IdentNode label;

    /** Statements. */
    private final Block body;

    /**
     * Constructor
     *
     * @param lineNumber line number
     * @param token      token
     * @param finish     finish
     * @param label      label identifier
     * @param body       body of label node
     */
    public LabelNode(final int lineNumber, final long token, final int finish, final IdentNode label, final Block body) {
        super(lineNumber, token, finish);

        this.label = label;
        this.body  = body;
    }

    private LabelNode(final LabelNode labelNode, final IdentNode label, final Block body) {
        super(labelNode);
        this.label = label;
        this.body  = body;
    }

    @Override
    public boolean isTerminal() {
        return body.isTerminal();
    }

    @Override
    public Node accept(final LexicalContext lc, final NodeVisitor<? extends LexicalContext> visitor) {
        if (visitor.enterLabelNode(this)) {
            return visitor.leaveLabelNode(
                setLabel(lc, (IdentNode)label.accept(visitor)).
                setBody(lc, (Block)body.accept(visitor)));
        }

        return this;
    }

    @Override
    public void toString(final StringBuilder sb) {
        label.toString(sb);
        sb.append(':');
    }

    /**
     * Get the body of the node
     * @return the body
     */
    public Block getBody() {
        return body;
    }

    /**
     * Reset the body of the node
     * @param lc lexical context
     * @param body new body
     * @return new for node if changed or existing if not
     */
    public LabelNode setBody(final LexicalContext lc, final Block body) {
        if (this.body == body) {
            return this;
        }
        return Node.replaceInLexicalContext(lc, this, new LabelNode(this, label, body));
    }

    /**
     * Get the identifier representing the label name
     * @return the label
     */
    public IdentNode getLabel() {
        return label;
    }

    private LabelNode setLabel(final LexicalContext lc, final IdentNode label) {
        if (this.label == label) {
            return this;
        }
        return Node.replaceInLexicalContext(lc, this, new LabelNode(this, label, body));
    }

}
