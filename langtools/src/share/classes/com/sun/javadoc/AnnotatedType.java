package com.sun.javadoc;


/**
 * Represents an annotated type.
 * For example:
 * <pre>
 *      {@code @NonNull String}
 *      {@code @Positive int}
 * </pre>
 */
public interface AnnotatedType extends Type {

    AnnotationDesc[] annotations();

    Type underlyingType();
}
