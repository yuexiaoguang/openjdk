package com.sun.source.doctree;

import java.util.List;

/**
 * A tree node for an @return block tag.
 *
 * <p>
 * &#064;return description
 */
@jdk.Exported
public interface ReturnTree extends BlockTagTree {
    List<? extends DocTree> getDescription();
}
