package com.sun.source.tree;

/**
 * A tree node for a wildcard type argument.
 * Use {@link #getKind getKind} to determine the kind of bound.
 *
 * For example:
 * <pre>
 *   ?
 *
 *   ? extends <em>bound</em>
 *
 *   ? super <em>bound</em>
 * </pre>
 */
@jdk.Exported
public interface WildcardTree extends Tree {
    Tree getBound();
}
