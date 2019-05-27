package com.sun.source.tree;

import java.util.List;

/**
 * A tree node for a 'case' in a 'switch' statement.
 *
 * For example:
 * <pre>
 *   case <em>expression</em> :
 *       <em>statements</em>
 *
 *   default :
 *       <em>statements</em>
 * </pre>
 */
@jdk.Exported
public interface CaseTree extends Tree {
    /**
     * @return null if and only if this Case is {@code default:}
     */
    ExpressionTree getExpression();
    List<? extends StatementTree> getStatements();
}
