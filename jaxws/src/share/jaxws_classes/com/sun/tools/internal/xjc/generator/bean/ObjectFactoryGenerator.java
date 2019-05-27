package com.sun.tools.internal.xjc.generator.bean;

import com.sun.codemodel.internal.JDefinedClass;
import com.sun.tools.internal.xjc.model.CElementInfo;

/**
 * Generates <code>ObjectFactory</code> then wraps it and provides
 * access to it.
 *
 * <p>
 * The ObjectFactory contains
 * factory methods for each schema derived content class
 */
public abstract class ObjectFactoryGenerator {
    /**
     * Adds code for the given {@link CElementInfo} to ObjectFactory.
     */
    abstract void populate( CElementInfo ei );

    /**
     * Adds code that is relevant to a given {@link ClassOutlineImpl} to
     * ObjectFactory.
     */
    abstract void populate( ClassOutlineImpl cc );

    /**
     * Returns a reference to the generated (public) ObjectFactory
     */
    public abstract JDefinedClass getObjectFactory();
}
