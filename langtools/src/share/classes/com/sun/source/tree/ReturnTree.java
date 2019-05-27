package com.sun.source.tree;

/**
 * A tree node for a 'return' statement.
 *
 * For example:
 * <pre>
 *   return;
 *   return <em>expression</em>;
 * </pre>
 */
@jdk.Exported
public interface ReturnTree extends StatementTree {
    ExpressionTree getExpression();
}
