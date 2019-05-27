package com.sun.tools.internal.ws.wsdl.framework;

import javax.xml.namespace.QName;

/**
 * A listener for parsing-related events.
 */
public interface ParserListener {
    public void ignoringExtension(Entity entity, QName name, QName parent);
    public void doneParsingEntity(QName element, Entity entity);
}
