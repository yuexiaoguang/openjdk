package com.sun.source.doctree;

import java.util.List;

/**
 *
 * A tree node for an @exception or &#064;throws block tag.
 * &#064;exception is a synonym for &#064;throws.
 *
 * <p>
 * &#064;exception class-name description <br>
 * &#064;throws class-name description
 */
@jdk.Exported
public interface ThrowsTree extends BlockTagTree {
    ReferenceTree getExceptionName();
    List<? extends DocTree> getDescription();
}
