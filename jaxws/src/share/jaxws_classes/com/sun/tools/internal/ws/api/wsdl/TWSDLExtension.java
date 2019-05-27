package com.sun.tools.internal.ws.api.wsdl;

/**
 * A WSDL extension
 *
 * @deprecated This interface is deprecated, will be removed in JAX-WS 2.2 RI.
 */
public interface TWSDLExtension {
    /**
     * Gives Parent {@link TWSDLExtensible} element
     */
    TWSDLExtensible getParent();
}
