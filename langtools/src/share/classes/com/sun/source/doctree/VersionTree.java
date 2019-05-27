package com.sun.source.doctree;

import java.util.List;

/**
 *
 * A tree node for an @version block tag.
 *
 * <p>
 * &#064;version version-text
 */
@jdk.Exported
public interface VersionTree extends BlockTagTree {
    List<? extends DocTree> getBody();
}
