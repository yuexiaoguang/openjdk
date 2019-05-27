package com.sun.source.tree;

/**
 * A tree node for an 'if' statement.
 *
 * For example:
 * <pre>
 *   if ( <em>condition</em> )
 *      <em>thenStatement</em>
 *
 *   if ( <em>condition</em> )
 *       <em>thenStatement</em>
 *   else
 *       <em>elseStatement</em>
 * </pre>
 */
@jdk.Exported
public interface IfTree extends StatementTree {
    ExpressionTree getCondition();
    StatementTree getThenStatement();
    /**
     * @return null if this if statement has no else branch.
     */
    StatementTree getElseStatement();
}
