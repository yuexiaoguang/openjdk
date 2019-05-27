package com.sun.source.tree;

/**
 * A tree node for a 'throw' statement.
 *
 * For example:
 * <pre>
 *   throw <em>expression</em>;
 * </pre>
 */
@jdk.Exported
public interface ThrowTree extends StatementTree {
    ExpressionTree getExpression();
}
