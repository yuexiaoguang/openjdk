package com.sun.source.doctree;

import java.util.List;

/**
 * A tree node for an @since block tag.
 *
 * <p>
 * &#064;since since-text
 */
@jdk.Exported
public interface SinceTree extends BlockTagTree {
    List<? extends DocTree> getBody();
}
