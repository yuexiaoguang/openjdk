package com.sun.xml.internal.bind.v2.model.runtime;

import java.lang.reflect.Type;
import java.util.Set;

import com.sun.xml.internal.bind.v2.model.core.ReferencePropertyInfo;

public interface RuntimeReferencePropertyInfo extends ReferencePropertyInfo<Type,Class>, RuntimePropertyInfo {
    Set<? extends RuntimeElement> getElements();
}
