package com.sun.tools.internal.ws.wsdl.framework;

/**
 * An exception signalling that an entity with the given name/id has already been defined.
 */
public class DuplicateEntityException extends ValidationException {

    public DuplicateEntityException(GloballyKnown entity) {
        super(
            "entity.duplicateWithType",
                entity.getElementName().getLocalPart(),
                entity.getName());
    }

    public DuplicateEntityException(Identifiable entity) {
        super(
            "entity.duplicateWithType",
                entity.getElementName().getLocalPart(),
                entity.getID());
    }

    public DuplicateEntityException(Entity entity, String name) {
        super(
            "entity.duplicateWithType",
                entity.getElementName().getLocalPart(), name);
    }

    public String getDefaultResourceBundleName() {
        return "com.sun.tools.internal.ws.resources.wsdl";
    }
}
