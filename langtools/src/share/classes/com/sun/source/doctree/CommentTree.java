package com.sun.source.doctree;

/**
 * An embedded HTML comment.
 *
 * <p>
 * {@literal <!-- text --> }
 */
@jdk.Exported
public interface CommentTree extends DocTree {
    String getBody();
}

