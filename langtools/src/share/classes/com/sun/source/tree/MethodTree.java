package com.sun.source.tree;

import java.util.List;
import javax.lang.model.element.Name;

/**
 * A tree node for a method or annotation type element declaration.
 *
 * For example:
 * <pre>
 *   <em>modifiers</em> <em>typeParameters</em> <em>type</em> <em>name</em>
 *      ( <em>parameters</em> )
 *      <em>body</em>
 *
 *   <em>modifiers</em> <em>type</em> <em>name</em> () default <em>defaultValue</em>
 * </pre>
 */
@jdk.Exported
public interface MethodTree extends Tree {
    ModifiersTree getModifiers();
    Name getName();
    Tree getReturnType();
    List<? extends TypeParameterTree> getTypeParameters();
    List<? extends VariableTree> getParameters();

    /**
     * Return an explicit receiver parameter ("this" parameter).
     *
     * @return an explicit receiver parameter ("this" parameter)
     * @since 1.8
     */
    VariableTree getReceiverParameter();

    List<? extends ExpressionTree> getThrows();
    BlockTree getBody();
    Tree getDefaultValue(); // for annotation types
}
