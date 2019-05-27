package com.sun.source.tree;

/**
 * A tree node for the conditional operator ? :.
 *
 * For example:
 * <pre>
 *   <em>condition</em> ? <em>trueExpression</em> : <em>falseExpression</em>
 * </pre>
 */
@jdk.Exported
public interface ConditionalExpressionTree extends ExpressionTree {
    ExpressionTree getCondition();
    ExpressionTree getTrueExpression();
    ExpressionTree getFalseExpression();
}
