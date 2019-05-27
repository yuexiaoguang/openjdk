package com.sun.xml.internal.bind.v2.model.runtime;

import java.lang.reflect.Type;

import javax.xml.bind.JAXBElement;

import com.sun.xml.internal.bind.v2.model.core.ElementInfo;

public interface RuntimeElementInfo extends ElementInfo<Type,Class>, RuntimeElement {
    RuntimeClassInfo getScope();

    RuntimeElementPropertyInfo getProperty();

    /**
     * Returns the custom {@link JAXBElement} class tailored this element.
     */
    Class<? extends JAXBElement> getType();

    /**
     * {@inheritDoc}
     */
    RuntimeNonElement getContentType();
}
