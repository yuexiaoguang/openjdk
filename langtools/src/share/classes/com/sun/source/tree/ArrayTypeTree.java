package com.sun.source.tree;

/**
 * A tree node for an array type.
 *
 * For example:
 * <pre>
 *   <em>type</em> []
 * </pre>
 */
@jdk.Exported
public interface ArrayTypeTree extends Tree {
    Tree getType();
}
