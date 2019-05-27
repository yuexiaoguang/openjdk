package com.sun.tools.internal.ws.wsdl.framework;

import javax.xml.namespace.QName;

/**
 * An action operating on an entity reference composed of a kind and a QName.
 */
public interface EntityReferenceAction {
    public void perform(Kind kind, QName name);
}
