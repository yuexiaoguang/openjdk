package com.sun.source.doctree;

/**
 * A tree node to a reference to a Java language element.
 *
 * <p>
 * package.class#field
 */
@jdk.Exported
public interface ReferenceTree extends DocTree {
    String getSignature();
}
