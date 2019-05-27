package com.sun.source.tree;

/**
 * A tree node for an expression statement.
 *
 * For example:
 * <pre>
 *   <em>expression</em> ;
 * </pre>
 */
@jdk.Exported
public interface ExpressionStatementTree extends StatementTree {
    ExpressionTree getExpression();
}
