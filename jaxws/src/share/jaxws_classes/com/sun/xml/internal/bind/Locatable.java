package com.sun.xml.internal.bind;

import com.sun.xml.internal.bind.annotation.XmlLocation;

import org.xml.sax.Locator;

/**
 * Optional interface implemented by JAXB objects to expose
 * location information from which an object is unmarshalled.
 *
 * <p>
 * This is used during JAXB RI 1.0.x.
 * In JAXB 2.0, use {@link XmlLocation}.
 */
public interface Locatable {
    /**
     * @return
     *      null if the location information is unavaiable,
     *      or otherwise return a immutable valid {@link Locator}
     *      object.
     */
    Locator sourceLocation();
}
