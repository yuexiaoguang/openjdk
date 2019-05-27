package com.sun.source.tree;

import java.util.List;

/**
 * A tree node for an annotation.
 *
 * For example:
 * <pre>
 *    {@code @}<em>annotationType</em>
 *    {@code @}<em>annotationType</em> ( <em>arguments</em> )
 * </pre>
 */
@jdk.Exported
public interface AnnotationTree extends ExpressionTree {
    Tree getAnnotationType();
    List<? extends ExpressionTree> getArguments();
}
