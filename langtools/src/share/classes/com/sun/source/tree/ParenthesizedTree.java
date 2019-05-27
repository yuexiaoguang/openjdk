package com.sun.source.tree;

/**
 * A tree node for a parenthesized expression.  Note: parentheses
 * not be preserved by the parser.
 *
 * For example:
 * <pre>
 *   ( <em>expression</em> )
 * </pre>
 */
@jdk.Exported
public interface ParenthesizedTree extends ExpressionTree {
    ExpressionTree getExpression();
}
