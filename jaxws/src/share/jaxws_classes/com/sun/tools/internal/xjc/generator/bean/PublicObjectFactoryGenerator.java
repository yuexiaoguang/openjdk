package com.sun.tools.internal.xjc.generator.bean;

import com.sun.codemodel.internal.JPackage;
import com.sun.tools.internal.xjc.model.CElementInfo;
import com.sun.tools.internal.xjc.model.Model;
import com.sun.tools.internal.xjc.outline.Aspect;

/**
 * Generates public ObjectFactory.
 */
final class PublicObjectFactoryGenerator extends ObjectFactoryGeneratorImpl {
    public PublicObjectFactoryGenerator(BeanGenerator outline, Model model, JPackage targetPackage) {
        super(outline, model, targetPackage);
    }

    void populate(CElementInfo ei) {
        populate(ei,Aspect.IMPLEMENTATION,Aspect.EXPOSED);
    }

    void populate(ClassOutlineImpl cc) {
        populate(cc,cc.ref);
    }
}
