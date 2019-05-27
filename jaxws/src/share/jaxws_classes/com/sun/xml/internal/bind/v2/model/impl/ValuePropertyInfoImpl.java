package com.sun.xml.internal.bind.v2.model.impl;

import com.sun.xml.internal.bind.v2.model.core.PropertyKind;
import com.sun.xml.internal.bind.v2.model.core.ValuePropertyInfo;

class ValuePropertyInfoImpl<TypeT,ClassDeclT,FieldT,MethodT>
    extends SingleTypePropertyInfoImpl<TypeT,ClassDeclT,FieldT,MethodT>
    implements ValuePropertyInfo<TypeT,ClassDeclT> {

    ValuePropertyInfoImpl(
        ClassInfoImpl<TypeT,ClassDeclT,FieldT,MethodT> parent,
        PropertySeed<TypeT,ClassDeclT,FieldT,MethodT> seed) {

        super(parent,seed);
    }

    public PropertyKind kind() {
        return PropertyKind.VALUE;
    }
}
