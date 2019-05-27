package jdk.nashorn.internal.ir;

import jdk.nashorn.internal.ir.annotations.Immutable;
import jdk.nashorn.internal.ir.visitor.NodeVisitor;

/**
 * TernaryNode nodes represent three operand operations (?:).
 */
@Immutable
public final class TernaryNode extends Expression {
    private final Expression test;

    private final Expression trueExpr;

    /** Third argument. */
    private final Expression falseExpr;

    /**
     * Constructor
     *
     * @param token     token
     * @param test      test expression
     * @param trueExpr  expression evaluated when test evaluates to true
     * @param falseExpr expression evaluated when test evaluates to true
     */
    public TernaryNode(final long token, final Expression test, final Expression trueExpr, final Expression falseExpr) {
        super(token, falseExpr.getFinish());
        this.test = test;
        this.trueExpr = trueExpr;
        this.falseExpr = falseExpr;
    }

    private TernaryNode(final TernaryNode ternaryNode, final Expression test, final Expression trueExpr, final Expression falseExpr) {
        super(ternaryNode);
        this.test = test;
        this.trueExpr = trueExpr;
        this.falseExpr = falseExpr;
    }

    @Override
    public Node accept(final NodeVisitor<? extends LexicalContext> visitor) {
        if (visitor.enterTernaryNode(this)) {
            final Expression newTest = (Expression)getTest().accept(visitor);
            final Expression newTrueExpr = (Expression)getTrueExpression().accept(visitor);
            final Expression newFalseExpr = (Expression)falseExpr.accept(visitor);
            return visitor.leaveTernaryNode(setTest(newTest).setTrueExpression(newTrueExpr).setFalseExpression1(newFalseExpr));
        }

        return this;
    }

    @Override
    public void toString(final StringBuilder sb) {
        final boolean testParen  = tokenType().needsParens(getTest().tokenType(), true);
        final boolean trueParen  = tokenType().needsParens(getTrueExpression().tokenType(), false);
        final boolean falseParen = tokenType().needsParens(getFalseExpression().tokenType(), false);

        if (testParen) {
            sb.append('(');
        }
        getTest().toString(sb);
        if (testParen) {
            sb.append(')');
        }

        sb.append(" ? ");

        if (trueParen) {
            sb.append('(');
        }
        getTrueExpression().toString(sb);
        if (trueParen) {
            sb.append(')');
        }

        sb.append(" : ");

        if (falseParen) {
            sb.append('(');
        }
        getFalseExpression().toString(sb);
        if (falseParen) {
            sb.append(')');
        }
    }

    @Override
    public boolean isLocal() {
        return getTest().isLocal()
                && getTrueExpression().isLocal()
                && getFalseExpression().isLocal();
    }

    /**
     * Get the test expression for this ternary expression, i.e. "x" in x ? y : z
     * @return the test expression
     */
    public Expression getTest() {
        return test;
    }

    /**
     * Get the true expression for this ternary expression, i.e. "y" in x ? y : z
     * @return the true expression
     */
    public Expression getTrueExpression() {
        return trueExpr;
    }

    /**
     * Get the false expression for this ternary expression, i.e. "z" in x ? y : z
     * @return the false expression
     */
    public Expression getFalseExpression() {
        return falseExpr;
    }

    /**
     * Set the test expression for this node
     * @param test new test expression
     * @return a node equivalent to this one except for the requested change.
     */
    public TernaryNode setTest(final Expression test) {
        if (this.test == test) {
            return this;
        }
        return new TernaryNode(this, test, trueExpr, falseExpr);
    }

    /**
     * Set the true expression for this node
     * @param trueExpr new true expression
     * @return a node equivalent to this one except for the requested change.
     */
    public TernaryNode setTrueExpression(final Expression trueExpr) {
        if (this.trueExpr == trueExpr) {
            return this;
        }
        return new TernaryNode(this, test, trueExpr, falseExpr);
    }

    /**
     * Set the false expression for this node
     * @param falseExpr new false expression
     * @return a node equivalent to this one except for the requested change.
     */
    public TernaryNode setFalseExpression1(final Expression falseExpr) {
        if (this.falseExpr == falseExpr) {
            return this;
        }
        return new TernaryNode(this, test, trueExpr, falseExpr);
    }
}
