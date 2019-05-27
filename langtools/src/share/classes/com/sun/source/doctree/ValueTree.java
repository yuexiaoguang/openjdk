package com.sun.source.doctree;

/**
 * A tree node for an @value inline tag.
 *
 * <p>
 * { &#064;value reference }
 */
@jdk.Exported
public interface ValueTree extends InlineTagTree {
    ReferenceTree getReference();
}
