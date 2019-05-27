package com.sun.tools.internal.ws.wsdl.framework;

import javax.xml.namespace.QName;

/**
 * An exception signalling that an entity with the given name/id does not exist.
 */
public class NoSuchEntityException extends ValidationException {

    public NoSuchEntityException(QName name) {
        super(
            "entity.notFoundByQName",
                name.getLocalPart(), name.getNamespaceURI());
    }

    public NoSuchEntityException(String id) {
        super("entity.notFoundByID", id);
    }

    public String getDefaultResourceBundleName() {
        return "com.sun.tools.internal.ws.resources.wsdl";
    }
}
