package com.sun.source.tree;

/**
 * A tree node for an 'assert' statement.
 *
 * For example:
 * <pre>
 *   assert <em>condition</em> ;
 *
 *   assert <em>condition</em> : <em>detail</em> ;
 * </pre>
 */
@jdk.Exported
public interface AssertTree extends StatementTree {
    ExpressionTree getCondition();
    ExpressionTree getDetail();
}
