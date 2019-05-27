package com.sun.xml.internal.bind.v2.model.impl;

import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import com.sun.xml.internal.bind.v2.model.core.BuiltinLeafInfo;
import com.sun.xml.internal.bind.v2.model.core.LeafInfo;
import com.sun.xml.internal.bind.v2.model.core.Element;
import com.sun.xml.internal.bind.v2.model.nav.Navigator;

/**
 * JAXB spec designates a few Java classes to be mapped to XML types
 * in a way that ignores restrictions placed on user-defined beans.
 */
public class BuiltinLeafInfoImpl<TypeT,ClassDeclT> extends LeafInfoImpl<TypeT,ClassDeclT> implements BuiltinLeafInfo<TypeT,ClassDeclT> {

    private final QName[] typeNames;

    protected BuiltinLeafInfoImpl(TypeT type, QName... typeNames) {
        super(type, typeNames.length>0?typeNames[0]:null);
        this.typeNames = typeNames;
    }

    /**
     * Returns all the type names recognized by this bean info.
     *
     * @return
     *      do not modify the returned array.
     */
    public final QName[] getTypeNames() {
        return typeNames;
    }

    /**
     * @deprecated always return false at this level.
     */
    public final boolean isElement() {
        return false;
    }

    /**
     * @deprecated always return null at this level.
     */
    public final QName getElementName() {
        return null;
    }

    /**
     * @deprecated always return null at this level.
     */
    public final Element<TypeT,ClassDeclT> asElement() {
        return null;
    }

    /**
     * Creates all the {@link BuiltinLeafInfoImpl}s as specified in the spec.
     *
     * {@link LeafInfo}s are all defined by the spec.
     */
    public static <TypeT,ClassDeclT>
    Map<TypeT,BuiltinLeafInfoImpl<TypeT,ClassDeclT>> createLeaves( Navigator<TypeT,ClassDeclT,?,?> nav ) {
        Map<TypeT,BuiltinLeafInfoImpl<TypeT,ClassDeclT>> leaves = new HashMap<TypeT,BuiltinLeafInfoImpl<TypeT,ClassDeclT>>();

        for( RuntimeBuiltinLeafInfoImpl<?> leaf : RuntimeBuiltinLeafInfoImpl.builtinBeanInfos ) {
            TypeT t = nav.ref(leaf.getClazz());
            leaves.put( t, new BuiltinLeafInfoImpl<TypeT,ClassDeclT>(t,leaf.getTypeNames()) );
        }

        return leaves;
    }
}
