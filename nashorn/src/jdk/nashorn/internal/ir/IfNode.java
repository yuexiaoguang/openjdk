package jdk.nashorn.internal.ir;

import jdk.nashorn.internal.ir.annotations.Immutable;
import jdk.nashorn.internal.ir.visitor.NodeVisitor;

/**
 * IR representation for an IF statement.
 */
@Immutable
public final class IfNode extends Statement {
    /** Test expression. */
    private final Expression test;

    /** Pass statements. */
    private final Block pass;

    /** Fail statements. */
    private final Block fail;

    /**
     * Constructor
     *
     * @param lineNumber line number
     * @param token      token
     * @param finish     finish
     * @param test       test
     * @param pass       block to execute when test passes
     * @param fail       block to execute when test fails or null
     */
    public IfNode(final int lineNumber, final long token, final int finish, final Expression test, final Block pass, final Block fail) {
        super(lineNumber, token, finish);
        this.test = test;
        this.pass = pass;
        this.fail = fail;
    }

    private IfNode(final IfNode ifNode, final Expression test, final Block pass, final Block fail) {
        super(ifNode);
        this.test = test;
        this.pass = pass;
        this.fail = fail;
    }

    @Override
    public boolean isTerminal() {
        return pass.isTerminal() && fail != null && fail.isTerminal();
    }

    @Override
    public Node accept(final NodeVisitor<? extends LexicalContext> visitor) {
        if (visitor.enterIfNode(this)) {
            return visitor.leaveIfNode(
                setTest((Expression)test.accept(visitor)).
                setPass((Block)pass.accept(visitor)).
                setFail(fail == null ? null : (Block)fail.accept(visitor)));
        }

        return this;
    }

    @Override
    public void toString(final StringBuilder sb) {
        sb.append("if (");
        test.toString(sb);
        sb.append(')');
    }

    /**
     * Get the else block of this IfNode
     * @return the else block, or null if none exists
     */
    public Block getFail() {
        return fail;
    }

    private IfNode setFail(final Block fail) {
        if (this.fail == fail) {
            return this;
        }
        return new IfNode(this, test, pass, fail);
    }

    /**
     * Get the then block for this IfNode
     * @return the then block
     */
    public Block getPass() {
        return pass;
    }

    private IfNode setPass(final Block pass) {
        if (this.pass == pass) {
            return this;
        }
        return new IfNode(this, test, pass, fail);
    }

    /**
     * Get the test expression for this IfNode
     * @return the test expression
     */
    public Expression getTest() {
        return test;
    }

    /**
     * Reset the test expression for this IfNode
     * @param test a new test expression
     * @return new or same IfNode
     */
    public IfNode setTest(final Expression test) {
        if (this.test == test) {
            return this;
        }
        return new IfNode(this, test, pass, fail);
    }
}
