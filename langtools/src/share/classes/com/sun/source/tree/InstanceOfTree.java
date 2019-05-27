package com.sun.source.tree;

/**
 * A tree node for an 'instanceof' expression.
 *
 * For example:
 * <pre>
 *   <em>expression</em> instanceof <em>type</em>
 * </pre>
 */
@jdk.Exported
public interface InstanceOfTree extends ExpressionTree {
    ExpressionTree getExpression();
    Tree getType();
}
