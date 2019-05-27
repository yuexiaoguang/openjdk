package com.sun.xml.internal.bind.v2.model.impl;

import java.lang.reflect.Type;

import com.sun.xml.internal.bind.v2.model.nav.Navigator;
import com.sun.xml.internal.bind.v2.model.runtime.RuntimeNonElement;
import com.sun.xml.internal.bind.v2.runtime.Transducer;

final class RuntimeAnyTypeImpl extends AnyTypeImpl<Type,Class> implements RuntimeNonElement {
    private RuntimeAnyTypeImpl() {
        super(Utils.REFLECTION_NAVIGATOR);
    }

    public <V> Transducer<V> getTransducer() {
        return null;
    }

    static final RuntimeNonElement theInstance = new RuntimeAnyTypeImpl();
}
