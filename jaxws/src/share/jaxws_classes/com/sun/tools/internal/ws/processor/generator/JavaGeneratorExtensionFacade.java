package com.sun.tools.internal.ws.processor.generator;

import com.sun.codemodel.internal.JMethod;
import com.sun.tools.internal.ws.api.TJavaGeneratorExtension;
import com.sun.tools.internal.ws.api.wsdl.TWSDLOperation;

public final class JavaGeneratorExtensionFacade extends TJavaGeneratorExtension {
    private final TJavaGeneratorExtension[] extensions;

    JavaGeneratorExtensionFacade(TJavaGeneratorExtension... extensions) {
        assert extensions != null;
        this.extensions = extensions;
    }

    public void writeMethodAnnotations(TWSDLOperation wsdlOperation, JMethod jMethod) {
        for (TJavaGeneratorExtension e : extensions) {
            e.writeMethodAnnotations(wsdlOperation, jMethod);
        }
    }
}
