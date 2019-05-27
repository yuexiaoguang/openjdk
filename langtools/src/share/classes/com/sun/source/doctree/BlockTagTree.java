package com.sun.source.doctree;

/**
 * A tree node used as the base class for the different types of
 * block tags.
 */
@jdk.Exported
public interface BlockTagTree extends DocTree {
    String getTagName();
}
