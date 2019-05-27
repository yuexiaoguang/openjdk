package com.sun.source.tree;

/**
 * A tree node for a 'synchronized' statement.
 *
 * For example:
 * <pre>
 *   synchronized ( <em>expression</em> )
 *       <em>block</em>
 * </pre>
 */
@jdk.Exported
public interface SynchronizedTree extends StatementTree {
    ExpressionTree getExpression();
    BlockTree getBlock();
}
