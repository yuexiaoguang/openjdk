package com.sun.xml.internal.bind.v2.model.impl;

/**
 * {@link PropertyInfo} that allows to add additional elements to the collection.
 */
public interface DummyPropertyInfo<T, C, F, M> {
    void addType(PropertyInfoImpl<T, C, F, M> info);
}
