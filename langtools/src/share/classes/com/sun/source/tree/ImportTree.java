package com.sun.source.tree;

/**
 * A tree node for an import statement.
 *
 * For example:
 * <pre>
 *   import <em>qualifiedIdentifier</em> ;
 *
 *   static import <em>qualifiedIdentifier</em> ;
 * </pre>
 */
@jdk.Exported
public interface ImportTree extends Tree {
    boolean isStatic();
    /**
     * @return a qualified identifier ending in "*" if and only if
     * this is an import-on-demand.
     */
    Tree getQualifiedIdentifier();
}
