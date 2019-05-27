package com.sun.javadoc;


/**
 * Represents an element of an annotation type.
 */
public interface AnnotationTypeElementDoc extends MethodDoc {

    /**
     * Returns the default value of this element.
     * Returns null if this element has no default.
     *
     * @return the default value of this element.
     */
    AnnotationValue defaultValue();
}
