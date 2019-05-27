package com.sun.source.tree;

import java.util.List;

/**
 * A tree node to declare a new instance of a class.
 *
 * For example:
 * <pre>
 *   new <em>identifier</em> ( )
 *
 *   new <em>identifier</em> ( <em>arguments</em> )
 *
 *   new <em>typeArguments</em> <em>identifier</em> ( <em>arguments</em> )
 *       <em>classBody</em>
 *
 *   <em>enclosingExpression</em>.new <em>identifier</em> ( <em>arguments</em> )
 * </pre>
 */
@jdk.Exported
public interface NewClassTree extends ExpressionTree {
    ExpressionTree getEnclosingExpression();
    List<? extends Tree> getTypeArguments();
    ExpressionTree getIdentifier();
    List<? extends ExpressionTree> getArguments();
    ClassTree getClassBody();
}
