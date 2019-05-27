package com.sun.source.tree;

import java.util.List;

/**
 * A tree node for a method invocation expression.
 *
 * For example:
 * <pre>
 *   <em>identifier</em> ( <em>arguments</em> )
 *
 *   this . <em>typeArguments</em> <em>identifier</em> ( <em>arguments</em> )
 * </pre>
 */
@jdk.Exported
public interface MethodInvocationTree extends ExpressionTree {
    List<? extends Tree> getTypeArguments();
    ExpressionTree getMethodSelect();
    List<? extends ExpressionTree> getArguments();
}
