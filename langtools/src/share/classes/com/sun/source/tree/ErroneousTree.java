package com.sun.source.tree;

import java.util.List;

/**
 * A tree node to stand in for a malformed expression.
 */
@jdk.Exported
public interface ErroneousTree extends ExpressionTree {
    List<? extends Tree> getErrorTrees();
}
