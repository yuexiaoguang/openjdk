package com.sun.source.doctree;

import java.util.List;

/**
 * A tree node for an @serial block tag.
 *
 * <p>
 * &#064;serial field-description | include | exclude
 */
@jdk.Exported
public interface SerialTree extends BlockTagTree {
    List<? extends DocTree> getDescription();
}
