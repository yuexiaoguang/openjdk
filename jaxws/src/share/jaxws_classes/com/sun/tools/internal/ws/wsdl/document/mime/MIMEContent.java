package com.sun.tools.internal.ws.wsdl.document.mime;

import com.sun.tools.internal.ws.wsdl.framework.ExtensionImpl;
import org.xml.sax.Locator;

import javax.xml.namespace.QName;

/**
 * A MIME content extension.
 */
public class MIMEContent extends ExtensionImpl {

    public MIMEContent(Locator locator) {
        super(locator);
    }

    public QName getElementName() {
        return MIMEConstants.QNAME_CONTENT;
    }

    public String getPart() {
        return _part;
    }

    public void setPart(String s) {
        _part = s;
    }

    public String getType() {
        return _type;
    }

    public void setType(String s) {
        _type = s;
    }

    public void validateThis() {
    }

    private String _part;
    private String _type;
}
