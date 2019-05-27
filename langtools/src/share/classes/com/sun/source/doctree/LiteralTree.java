package com.sun.source.doctree;

/**
 *
 * A tree node for an @literal or @code inline tag.
 *
 * <p>
 * {&#064;literal text}
 */
@jdk.Exported
public interface LiteralTree extends InlineTagTree {
    TextTree getBody();
}
