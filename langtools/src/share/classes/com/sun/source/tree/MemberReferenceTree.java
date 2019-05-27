package com.sun.source.tree;

import java.util.List;

import javax.lang.model.element.Name;

/**
 * A tree node for a member reference expression.
 *
 * For example:
 * <pre>
 *   <em>expression</em> # <em>[ identifier | new ]</em>
 * </pre>
 */
@jdk.Exported
public interface MemberReferenceTree extends ExpressionTree {

    /**
     * There are two kinds of member references: (i) method references and
     * (ii) constructor references
     */
    @jdk.Exported
    public enum ReferenceMode {
        /** enum constant for method references */
        INVOKE,
        /** enum constant for constructor references */
        NEW
    }
    ReferenceMode getMode();
    ExpressionTree getQualifierExpression();
    Name getName();
    List<? extends ExpressionTree> getTypeArguments();
}
