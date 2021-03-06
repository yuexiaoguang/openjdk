package com.sun.source.doctree;

import java.util.List;

/**
 * A tree node for an unrecognized inline tag.
 *
 * <p>
 * {&#064;name content}
 */
@jdk.Exported
public interface UnknownInlineTagTree extends InlineTagTree {
    List<? extends DocTree> getContent();
}
