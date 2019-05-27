package com.sun.source.doctree;

import javax.lang.model.element.Name;

/**
 * An identifier in a documentation comment.
 *
 * <p>
 * name
 */
@jdk.Exported
public interface IdentifierTree extends DocTree {
    Name getName();
}
