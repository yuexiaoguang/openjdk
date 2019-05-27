package com.sun.source.doctree;

import java.util.List;

/**
 * The top level representation of a documentation comment.
 *
 * <p>
 * first-sentence body block-tags
 */
@jdk.Exported
public interface DocCommentTree extends DocTree {
    List<? extends DocTree> getFirstSentence();
    List<? extends DocTree> getBody();
    List<? extends DocTree> getBlockTags();
}
