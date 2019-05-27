package com.sun.tools.internal.ws.wsdl.framework;

import javax.xml.namespace.QName;

/**
 * An action operating on a QName.
 */
public interface QNameAction {
    public void perform(QName name);
}
