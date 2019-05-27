package com.sun.tools.internal.ws.api.wsdl;

import com.sun.codemodel.internal.JClass;

import java.util.Map;

/**
 * Abstracts wsdl:portType/wsdl:operation
 *
 * @deprecated This interface is deprecated, will be removed in JAX-WS 2.2 RI.
 */
public interface TWSDLOperation extends TWSDLExtensible{
    /**
     * Gives a Map of fault name attribute value to the {@link JClass}
     */
    Map<String, JClass> getFaults();
}
