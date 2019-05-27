package com.sun.source.tree;

import javax.lang.model.element.Name;

/**
 * A tree node for a member access expression.
 *
 * For example:
 * <pre>
 *   <em>expression</em> . <em>identifier</em>
 * </pre>
 */
@jdk.Exported
public interface MemberSelectTree extends ExpressionTree {
    ExpressionTree getExpression();
    Name getIdentifier();
}
