package com.sun.source.doctree;

import java.util.List;
import javax.lang.model.element.Name;

/**
 * A tree node for an attribute in an HTML element.
 */
@jdk.Exported
public interface AttributeTree extends DocTree {
    @jdk.Exported
    enum ValueKind { EMPTY, UNQUOTED, SINGLE, DOUBLE };

    Name getName();
    ValueKind getValueKind();
    List<? extends DocTree> getValue();
}
