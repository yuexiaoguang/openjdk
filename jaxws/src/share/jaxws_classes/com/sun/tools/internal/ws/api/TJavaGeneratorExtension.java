package com.sun.tools.internal.ws.api;

import com.sun.codemodel.internal.JMethod;
import com.sun.tools.internal.ws.api.wsdl.TWSDLOperation;
import com.sun.tools.internal.ws.processor.generator.JavaGeneratorExtensionFacade;

/**
 * Provides Java SEI Code generation Extensiblity mechanism.
 *
 * @deprecated This class is deprecated, will be removed in JAX-WS 2.2 RI.
 */
public abstract class TJavaGeneratorExtension {
    /**
     * This method should be used to write annotations on {@link JMethod}.
     *
     * @param wsdlOperation non-null wsdl extensiblity element -  wsdl:portType/wsdl:operation.
     * @param jMethod non-null {@link JMethod}
     */
     public abstract void writeMethodAnnotations(TWSDLOperation wsdlOperation, JMethod jMethod);
}
