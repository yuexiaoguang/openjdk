package jdk.nashorn.internal.ir;

import jdk.nashorn.internal.ir.annotations.Immutable;
import jdk.nashorn.internal.ir.visitor.NodeVisitor;

/**
 * IR representation for executing bare expressions. Basically, an expression
 * node means "this code will be executed" and evaluating it results in
 * statements being added to the IR
 */
@Immutable
public final class ExpressionStatement extends Statement {
    /** Expression to execute. */
    private final Expression expression;

    /**
     * Constructor
     *
     * @param lineNumber line number
     * @param token      token
     * @param finish     finish
     * @param expression the expression to execute
     */
    public ExpressionStatement(final int lineNumber, final long token, final int finish, final Expression expression) {
        super(lineNumber, token, finish);
        this.expression = expression;
    }

    private ExpressionStatement(final ExpressionStatement expressionStatement, final Expression expression) {
        super(expressionStatement);
        this.expression = expression;
    }

    @Override
    public boolean isTerminal() {
        return expression.isTerminal();
    }

    @Override
    public Node accept(final NodeVisitor<? extends LexicalContext> visitor) {
        if (visitor.enterExpressionStatement(this)) {
            return visitor.leaveExpressionStatement(setExpression((Expression)expression.accept(visitor)));
        }

        return this;
    }

    @Override
    public void toString(final StringBuilder sb) {
        expression.toString(sb);
    }

    /**
     * Return the expression to be executed
     * @return the expression
     */
    public Expression getExpression() {
        return expression;
    }

    /**
     * Reset the expression to be executed
     * @param expression the expression
     * @return new or same execute node
     */
    public ExpressionStatement setExpression(final Expression expression) {
        if (this.expression == expression) {
            return this;
        }
        return new ExpressionStatement(this, expression);
    }
}
