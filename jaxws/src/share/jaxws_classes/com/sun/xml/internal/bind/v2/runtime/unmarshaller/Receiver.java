package com.sun.xml.internal.bind.v2.runtime.unmarshaller;

import org.xml.sax.SAXException;

/**
 * Receives an object by a child {@link Loader}.
 */
public interface Receiver {
    /**
     * Called when the child loader is deactivated.
     *
     * @param state
     *      points to the parent's current state.
     * @param o
     *      object that was loaded. may be null.
     */
    void receive(UnmarshallingContext.State state, Object o) throws SAXException;
}
