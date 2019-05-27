package com.sun.xml.internal.bind.v2.model.runtime;

import java.lang.reflect.Type;

import com.sun.xml.internal.bind.v2.model.core.AttributePropertyInfo;

public interface RuntimeAttributePropertyInfo extends AttributePropertyInfo<Type,Class>, RuntimePropertyInfo, RuntimeNonElementRef {
    // refinement
    RuntimeNonElement getTarget();
}
