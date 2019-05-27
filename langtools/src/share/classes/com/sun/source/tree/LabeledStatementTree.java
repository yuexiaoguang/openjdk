package com.sun.source.tree;

import javax.lang.model.element.Name;

/**
 * A tree node for a labeled statement.
 *
 * For example:
 * <pre>
 *   <em>label</em> : <em>statement</em>
 * </pre>
 */
@jdk.Exported
public interface LabeledStatementTree extends StatementTree {
    Name getLabel();
    StatementTree getStatement();
}
