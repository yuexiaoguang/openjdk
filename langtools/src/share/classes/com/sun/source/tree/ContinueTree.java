package com.sun.source.tree;

import javax.lang.model.element.Name;

/**
 * A tree node for a 'continue' statement.
 *
 * For example:
 * <pre>
 *   continue;
 *   continue <em>label</em> ;
 * </pre>
 */
@jdk.Exported
public interface ContinueTree extends StatementTree {
    Name getLabel();
}
