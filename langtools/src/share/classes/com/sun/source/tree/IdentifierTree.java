package com.sun.source.tree;

import javax.lang.model.element.Name;

/**
 * A tree node for an identifier expression.
 *
 * For example:
 * <pre>
 *   <em>name</em>
 * </pre>
 */
@jdk.Exported
public interface IdentifierTree extends ExpressionTree {
    Name getName();
}
