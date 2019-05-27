package com.sun.source.doctree;

import java.util.List;

/**
 * A tree node for an @author block tag.
 *
 * <p>
 * &#064;author name-text.
 */
@jdk.Exported
public interface AuthorTree extends BlockTagTree {
    List<? extends DocTree> getName();
}
