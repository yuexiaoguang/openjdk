package com.sun.source.tree;

import java.util.List;

/**
 * A tree node for an intersection type in a cast expression.
 */
@jdk.Exported
public interface IntersectionTypeTree extends Tree {
    List<? extends Tree> getBounds();
}
