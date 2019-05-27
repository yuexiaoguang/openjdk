package com.sun.tools.internal.xjc.model;

import javax.xml.namespace.QName;

import com.sun.tools.internal.xjc.model.nav.NClass;
import com.sun.tools.internal.xjc.model.nav.NType;
import com.sun.xml.internal.bind.v2.model.core.PropertyKind;
import com.sun.xml.internal.bind.v2.model.core.ValuePropertyInfo;
import com.sun.xml.internal.xsom.XSComponent;

import org.xml.sax.Locator;

/**
 * {@link ValuePropertyInfo} implementation for XJC.
 */
public final class CValuePropertyInfo extends CSingleTypePropertyInfo implements ValuePropertyInfo<NType,NClass> {
    public CValuePropertyInfo(String name, XSComponent source, CCustomizations customizations, Locator locator, TypeUse type, QName typeName) {
        super(name, type, typeName, source, customizations, locator);
    }

    public final PropertyKind kind() {
        return  PropertyKind.VALUE;
    }

    public <V> V accept(CPropertyVisitor<V> visitor) {
        return visitor.onValue(this);
    }
}
