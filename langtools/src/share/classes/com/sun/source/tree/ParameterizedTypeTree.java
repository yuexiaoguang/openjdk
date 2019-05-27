package com.sun.source.tree;

import java.util.List;

/**
 * A tree node for a type expression involving type parameters.
 *
 * For example:
 * <pre>
 *   <em>type</em> &lt; <em>typeArguments</em> &gt;
 * </pre>
 */
@jdk.Exported
public interface ParameterizedTypeTree extends Tree {
    Tree getType();
    List<? extends Tree> getTypeArguments();
}
