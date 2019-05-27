package com.sun.source.doctree;

import java.util.List;
import javax.lang.model.element.Name;

/**
 * A tree node for the start of an HTML element.
 *
 * <p>
 * &lt; name [attributes] [/]&gt;
 */
@jdk.Exported
public interface StartElementTree extends DocTree {
    Name getName();
    List<? extends DocTree> getAttributes();
    boolean isSelfClosing();
}
