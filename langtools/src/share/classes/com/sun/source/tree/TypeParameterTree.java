package com.sun.source.tree;

import java.util.List;
import javax.lang.model.element.Name;

/**
 * A tree node for a type parameter.
 *
 * For example:
 * <pre>
 *   <em>name</em>
 *
 *   <em>name</em> extends <em>bounds</em>
 *
 *   <em>annotations</em> <em>name</em>
 * </pre>
 */
@jdk.Exported
public interface TypeParameterTree extends Tree {
    Name getName();
    List<? extends Tree> getBounds();

    /**
     * Return annotations on the type parameter declaration.
     *
     * Annotations need Target meta-annotations of
     * {@link java.lang.annotation.ElementType#TYPE_PARAMETER} or
     * {@link java.lang.annotation.ElementType#TYPE_USE}
     * to appear in this position.
     *
     * @return annotations on the type parameter declaration
     * @since 1.8
     */
    List<? extends AnnotationTree> getAnnotations();
}
