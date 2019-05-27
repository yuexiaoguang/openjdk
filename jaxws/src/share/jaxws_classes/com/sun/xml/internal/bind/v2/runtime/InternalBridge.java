package com.sun.xml.internal.bind.v2.runtime;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import com.sun.xml.internal.bind.api.Bridge;

import org.xml.sax.SAXException;

/**
 * Additional methods on {@link Bridge} that are only available for the JAXB runtime.
 */
abstract class InternalBridge<T> extends Bridge<T> {
    protected InternalBridge(JAXBContextImpl context) {
        super(context);
    }

    public JAXBContextImpl getContext() {
        return context;
    }

    /**
     * Called from {@link CompositeStructureBeanInfo} to marshal this bridge as a sub-tree.
     */
    abstract void marshal( T o, XMLSerializer out ) throws IOException, SAXException, XMLStreamException;
}
