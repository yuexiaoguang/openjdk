package com.sun.codemodel.internal;

/**
 * Program elements that can have Javadoc
 */
public interface JDocCommentable {
    /**
     * @return the JavaDoc of the Element
     */
    JDocComment javadoc();
}
