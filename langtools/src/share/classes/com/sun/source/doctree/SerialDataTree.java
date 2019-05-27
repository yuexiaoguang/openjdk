package com.sun.source.doctree;

import java.util.List;

/**
 * A tree node for an @serialData block tag.
 *
 * <p>
 * &#064;serialData data-description
 */
@jdk.Exported
public interface SerialDataTree extends BlockTagTree {
    List<? extends DocTree> getDescription();
}
