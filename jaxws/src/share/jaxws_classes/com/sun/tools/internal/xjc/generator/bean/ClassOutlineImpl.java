package com.sun.tools.internal.xjc.generator.bean;

import com.sun.codemodel.internal.JClass;
import com.sun.codemodel.internal.JDefinedClass;
import com.sun.tools.internal.xjc.model.CClassInfo;
import com.sun.tools.internal.xjc.outline.ClassOutline;

/**
 * {@link ClassOutline} enhanced with schema2java specific
 * information.
 */
public final class ClassOutlineImpl extends ClassOutline {
    private final BeanGenerator _parent;


    public MethodWriter createMethodWriter() {
        return _parent.getModel().strategy.createMethodWriter(this);
    }

    /**
     * Gets {@link #_package} as {@link PackageOutlineImpl},
     * since it's guaranteed to be of that type.
     */
    public PackageOutlineImpl _package() {
        return (PackageOutlineImpl)super._package();
    }

    ClassOutlineImpl( BeanGenerator _parent,
        CClassInfo _target, JDefinedClass exposedClass, JDefinedClass _implClass, JClass _implRef ) {
        super(_target,exposedClass,_implRef,_implClass);
        this._parent = _parent;
        _package().classes.add(this);
    }

    public BeanGenerator parent() {
        return _parent;
    }
}
