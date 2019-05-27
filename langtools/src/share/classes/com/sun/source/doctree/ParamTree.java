package com.sun.source.doctree;

import java.util.List;

/**
 * A tree node for an @param block tag.
 *
 * <p>
 * &#064;param parameter-name description
 */
@jdk.Exported
public interface ParamTree extends BlockTagTree {
    boolean isTypeParameter();
    IdentifierTree getName();
    List<? extends DocTree> getDescription();
}
