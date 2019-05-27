package com.sun.xml.internal.bind.v2.model.runtime;

import java.lang.reflect.Type;

import com.sun.xml.internal.bind.v2.model.core.MapPropertyInfo;

public interface RuntimeMapPropertyInfo extends RuntimePropertyInfo, MapPropertyInfo<Type,Class> {
    RuntimeNonElement getKeyType();
    RuntimeNonElement getValueType();
}
