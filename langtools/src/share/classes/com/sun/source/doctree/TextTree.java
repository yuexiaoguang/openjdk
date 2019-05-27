package com.sun.source.doctree;

/**
 * A tree node for plain text.
 */
@jdk.Exported
public interface TextTree extends DocTree {
    String getBody();
}
