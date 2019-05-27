package com.sun.source.tree;

import java.util.List;

/**
 * A tree node for a 'try' statement.
 *
 * For example:
 * <pre>
 *   try
 *       <em>block</em>
 *   <em>catches</em>
 *   finally
 *       <em>finallyBlock</em>
 * </pre>
 */
@jdk.Exported
public interface TryTree extends StatementTree {
    BlockTree getBlock();
    List<? extends CatchTree> getCatches();
    BlockTree getFinallyBlock();
    List<? extends Tree> getResources();
}
