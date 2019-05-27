package com.sun.source.doctree;

import javax.lang.model.element.Name;

/**
 * A tree node for the end of an HTML element.
 *
 * <p>
 * &lt;/ name &gt;
 */
@jdk.Exported
public interface EndElementTree extends DocTree {
    Name getName();
}
