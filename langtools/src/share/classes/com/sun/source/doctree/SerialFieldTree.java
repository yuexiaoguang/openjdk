package com.sun.source.doctree;

import java.util.List;

/**
 * A tree node for an @serialData block tag.
 *
 * <p>
 * &#064;serialField field-name field-type field-description
 */
@jdk.Exported
public interface SerialFieldTree extends BlockTagTree {
    IdentifierTree getName();
    ReferenceTree getType();
    List<? extends DocTree> getDescription();
}
