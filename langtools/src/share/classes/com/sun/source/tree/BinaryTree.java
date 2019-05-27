package com.sun.source.tree;

/**
 * A tree node for a binary expression.
 * Use {@link #getKind getKind} to determine the kind of operator.
 *
 * For example:
 * <pre>
 *   <em>leftOperand</em> <em>operator</em> <em>rightOperand</em>
 * </pre>
 */
@jdk.Exported
public interface BinaryTree extends ExpressionTree {
    ExpressionTree getLeftOperand();
    ExpressionTree getRightOperand();
}
