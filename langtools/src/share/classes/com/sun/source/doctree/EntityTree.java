package com.sun.source.doctree;

import javax.lang.model.element.Name;


/**
 * A tree node for an HTML entity.
 *
 * <p>
 * &amp; name ;
 */
@jdk.Exported
public interface EntityTree extends DocTree {
    Name getName();
}
