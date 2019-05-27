package com.sun.source.tree;

import java.util.List;

/**
 * A tree node for a statement block.
 *
 * For example:
 * <pre>
 *   { }
 *
 *   { <em>statements</em> }
 *
 *   static { <em>statements</em> }
 * </pre>
 */
@jdk.Exported
public interface BlockTree extends StatementTree {
    boolean isStatic();
    List<? extends StatementTree> getStatements();
}
