package com.sun.source.doctree;

import java.util.List;

/**
 * A tree node for an @deprecated block tag.
 *
 * <p>
 * &#064;deprecated deprecated text.
 */
@jdk.Exported
public interface DeprecatedTree extends BlockTagTree {
    List<? extends DocTree> getBody();
}
