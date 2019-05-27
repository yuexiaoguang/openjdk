package com.sun.source.tree;

import javax.lang.model.element.Name;

/**
 * A tree node for a variable declaration.
 *
 * For example:
 * <pre>
 *   <em>modifiers</em> <em>type</em> <em>name</em> <em>initializer</em> ;
 *   <em>modifiers</em> <em>type</em> <em>qualified-name</em>.this
 * </pre>
 */
@jdk.Exported
public interface VariableTree extends StatementTree {
    ModifiersTree getModifiers();
    Name getName();
    ExpressionTree getNameExpression();
    Tree getType();
    ExpressionTree getInitializer();
}
