package com.sun.tools.internal.ws.wsdl.framework;

import com.sun.tools.internal.ws.api.wsdl.TWSDLExtension;

/**
 * A base class for extension visitors.
 */
public class ExtensionVisitorBase implements ExtensionVisitor {
    public ExtensionVisitorBase() {
    }

    public void preVisit(TWSDLExtension extension) throws Exception {
    }
    public void postVisit(TWSDLExtension extension) throws Exception {
    }
}
