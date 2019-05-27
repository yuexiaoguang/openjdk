package com.sun.source.tree;

/**
 * A tree node for a 'catch' block in a 'try' statement.
 *
 * For example:
 * <pre>
 *   catch ( <em>parameter</em> )
 *       <em>block</em>
 * </pre>
 */
@jdk.Exported
public interface CatchTree extends Tree {
    VariableTree getParameter();
    BlockTree getBlock();
}
