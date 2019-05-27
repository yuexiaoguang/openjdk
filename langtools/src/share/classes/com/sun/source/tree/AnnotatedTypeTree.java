package com.sun.source.tree;

import java.util.List;

/**
 * A tree node for an annotated type
 *
 * For example:
 * <pre>
 *    {@code @}<em>annotationType String</em>
 *    {@code @}<em>annotationType</em> ( <em>arguments</em> ) <em>Date</em>
 * </pre>
 */
@jdk.Exported
public interface AnnotatedTypeTree extends ExpressionTree {
    List<? extends AnnotationTree> getAnnotations();
    ExpressionTree getUnderlyingType();
}
