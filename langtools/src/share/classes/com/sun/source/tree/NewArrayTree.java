package com.sun.source.tree;

import java.util.List;

/**
 * A tree node for an expression to create a new instance of an array.
 *
 * For example:
 * <pre>
 *   new <em>type</em> <em>dimensions</em> <em>initializers</em>
 *
 *   new <em>type</em> <em>dimensions</em> [ ] <em>initializers</em>
 * </pre>
 */
@jdk.Exported
public interface NewArrayTree extends ExpressionTree {
    Tree getType();
    List<? extends ExpressionTree> getDimensions();
    List<? extends ExpressionTree> getInitializers();
    List<? extends AnnotationTree> getAnnotations();
    List<? extends List<? extends AnnotationTree>> getDimAnnotations();
}
