package com.sun.tools.internal.ws.wsdl.framework;

import com.sun.tools.internal.ws.api.wsdl.TWSDLExtension;

/**
 * A visitor working on extension entities.
 */
public interface ExtensionVisitor {
    public void preVisit(TWSDLExtension extension) throws Exception;
    public void postVisit(TWSDLExtension extension) throws Exception;
}
