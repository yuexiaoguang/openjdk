package com.sun.source.tree;

/**
 * A tree node for a 'do' statement.
 *
 * For example:
 * <pre>
 *   do
 *       <em>statement</em>
 *   while ( <em>expression</em> );
 * </pre>
 */
@jdk.Exported
public interface DoWhileLoopTree extends StatementTree {
    ExpressionTree getCondition();
    StatementTree getStatement();
}
