package jdk.nashorn.internal.ir;

import jdk.nashorn.internal.ir.annotations.Immutable;
import jdk.nashorn.internal.ir.visitor.NodeVisitor;

/**
 * IR representation for a WHILE statement. This is the superclass of all
 * loop nodes
 */
@Immutable
public final class WhileNode extends LoopNode {

    /** is this a do while node ? */
    private final boolean isDoWhile;

    /**
     * Constructor
     *
     * @param lineNumber line number
     * @param token      token
     * @param finish     finish
     * @param isDoWhile  is this a do while loop?
     */
    public WhileNode(final int lineNumber, final long token, final int finish, final boolean isDoWhile) {
        super(lineNumber, token, finish, null, null, false);
        this.isDoWhile = isDoWhile;
    }

    /**
     * Internal copy constructor
     *
     * @param whileNode while node
     * @param test      test
     * @param body      body
     * @param controlFlowEscapes control flow escapes?
     */
    protected WhileNode(final WhileNode whileNode, final Expression test, final Block body, final boolean controlFlowEscapes) {
        super(whileNode, test, body, controlFlowEscapes);
        this.isDoWhile = whileNode.isDoWhile;
    }

    @Override
    public Node ensureUniqueLabels(final LexicalContext lc) {
        return Node.replaceInLexicalContext(lc, this, new WhileNode(this, test, body, controlFlowEscapes));
    }

    @Override
    public boolean hasGoto() {
        return test == null;
    }

    @Override
    public Node accept(final LexicalContext lc, final NodeVisitor<? extends LexicalContext> visitor) {
        if (visitor.enterWhileNode(this)) {
            if (isDoWhile()) {
                return visitor.leaveWhileNode(
                        setBody(lc, (Block)body.accept(visitor)).
                        setTest(lc, (Expression)test.accept(visitor)));
            }
            return visitor.leaveWhileNode(
                    setTest(lc, (Expression)test.accept(visitor)).
                    setBody(lc, (Block)body.accept(visitor)));
        }
        return this;
    }

    @Override
    public Expression getTest() {
        return test;
    }

    @Override
    public WhileNode setTest(final LexicalContext lc, final Expression test) {
        if (this.test == test) {
            return this;
        }
        return Node.replaceInLexicalContext(lc, this, new WhileNode(this, test, body, controlFlowEscapes));
    }

    @Override
    public Block getBody() {
        return body;
    }

    @Override
    public WhileNode setBody(final LexicalContext lc, final Block body) {
        if (this.body == body) {
            return this;
        }
        return Node.replaceInLexicalContext(lc, this, new WhileNode(this, test, body, controlFlowEscapes));
    }

    @Override
    public WhileNode setControlFlowEscapes(final LexicalContext lc, final boolean controlFlowEscapes) {
        if (this.controlFlowEscapes == controlFlowEscapes) {
            return this;
        }
        return Node.replaceInLexicalContext(lc, this, new WhileNode(this, test, body, controlFlowEscapes));
    }

    /**
     * Check if this is a do while loop or a normal while loop
     * @return true if do while
     */
    public boolean isDoWhile() {
        return isDoWhile;
    }

    @Override
    public void toString(final StringBuilder sb) {
        sb.append("while (");
        test.toString(sb);
        sb.append(')');
    }

    @Override
    public boolean mustEnter() {
        if (isDoWhile()) {
            return true;
        }
        return test == null;
    }
}
