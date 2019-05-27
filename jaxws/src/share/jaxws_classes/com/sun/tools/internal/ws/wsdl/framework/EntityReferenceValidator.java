package com.sun.tools.internal.ws.wsdl.framework;

import javax.xml.namespace.QName;

/**
 * An interface implemented by a class that is capable of validating
 * a QName/Kind pair referring to an external entity.
 */
public interface EntityReferenceValidator {
    public boolean isValid(Kind kind, QName name);
}
