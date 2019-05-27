package com.sun.tools.internal.xjc.reader.dtd;

import java.util.LinkedHashSet;
import java.util.Set;

final class Block {
    final boolean isOptional;
    final boolean isRepeated;

    /**
     * {@link Element}s that belong to this block.
     * <p>
     * We want to preserve the order they are added, but we don't want
     * dupliates.
     */
    final Set<Element> elements = new LinkedHashSet<Element>();

    Block(boolean optional, boolean repeated) {
        isOptional = optional;
        isRepeated = repeated;
    }
}
