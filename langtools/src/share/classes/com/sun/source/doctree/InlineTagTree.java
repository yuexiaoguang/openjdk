package com.sun.source.doctree;

/**
 * A tree node used as the base class for the different types of
 * inline tags.
 */
@jdk.Exported
public interface InlineTagTree extends DocTree {
    String getTagName();
}
