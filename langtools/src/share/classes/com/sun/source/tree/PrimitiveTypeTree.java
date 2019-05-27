package com.sun.source.tree;

import javax.lang.model.type.TypeKind;

/**
 * A tree node for a primitive type.
 *
 * For example:
 * <pre>
 *   <em>primitiveTypeKind</em>
 * </pre>
 */
@jdk.Exported
public interface PrimitiveTypeTree extends Tree {
    TypeKind getPrimitiveTypeKind();
}
