package com.sun.source.tree;

import java.util.List;

/**
 * A tree node for a union type expression in a multicatch var declaration.
 */
@jdk.Exported
public interface UnionTypeTree extends Tree {
    List<? extends Tree> getTypeAlternatives();
}
