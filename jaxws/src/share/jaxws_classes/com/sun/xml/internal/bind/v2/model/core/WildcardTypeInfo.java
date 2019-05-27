package com.sun.xml.internal.bind.v2.model.core;

/**
 * Type referenced as a result of having the wildcard.
 *
 * TODO: think about how to gracefully handle the difference between LAX,SKIP, and STRICT.
 */
public interface WildcardTypeInfo<T,C> extends TypeInfo<T,C> {
}
