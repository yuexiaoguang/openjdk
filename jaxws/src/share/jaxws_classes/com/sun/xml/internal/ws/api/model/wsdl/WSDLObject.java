package com.sun.xml.internal.ws.api.model.wsdl;

import com.sun.istack.internal.NotNull;
import org.xml.sax.Locator;

/**
 * Marker interface serves as base interface for the wsdl model
 */
public interface WSDLObject {
    /**
     * Gets the source location information in the parsed WSDL.
     *
     * This is useful when producing error messages.
     */
    @NotNull
    Locator getLocation();
}
