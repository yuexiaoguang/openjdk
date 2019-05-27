package com.sun.source.doctree;

import java.util.List;

/**
 * A tree node for an @link or &#064;linkplain inline tag.
 *
 * <p>
 * {&#064;link reference label} <br>
 * {&#064;linkplain reference label }
 */
@jdk.Exported
public interface LinkTree extends InlineTagTree {
    ReferenceTree getReference();
    List<? extends DocTree> getLabel();
}
