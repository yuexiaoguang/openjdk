package com.sun.tools.internal.ws.wsdl.document;

import com.sun.tools.internal.ws.api.wsdl.TWSDLExtensible;
import com.sun.tools.internal.ws.api.wsdl.TWSDLExtension;
import com.sun.tools.internal.ws.wsdl.framework.Entity;
import com.sun.tools.internal.ws.wsdl.framework.EntityAction;
import com.sun.tools.internal.ws.wsdl.framework.ExtensibilityHelper;
import org.xml.sax.Locator;

import javax.xml.namespace.QName;

/**
 * Entity corresponding to the "output" child element of a binding operation.
 */
public class BindingOutput extends Entity implements TWSDLExtensible {

    public BindingOutput(Locator locator) {
        super(locator);
        _helper = new ExtensibilityHelper();
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public QName getElementName() {
        return WSDLConstants.QNAME_OUTPUT;
    }

    public Documentation getDocumentation() {
        return _documentation;
    }

    public void setDocumentation(Documentation d) {
        _documentation = d;
    }

    public String getNameValue() {
        return getName();
    }

    public String getNamespaceURI() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public QName getWSDLElementName() {
        return getElementName();
    }

    public void addExtension(TWSDLExtension e) {
        _helper.addExtension(e);
    }

    public Iterable<TWSDLExtension> extensions() {
        return _helper.extensions();
    }

    public TWSDLExtensible getParent() {
        return parent;
    }

    public void withAllSubEntitiesDo(EntityAction action) {
        _helper.withAllSubEntitiesDo(action);
    }

    public void accept(WSDLDocumentVisitor visitor) throws Exception {
        visitor.preVisit(this);
        _helper.accept(visitor);
        visitor.postVisit(this);
    }

    public void validateThis() {
    }


    public void setParent(TWSDLExtensible parent) {
        this.parent = parent;
    }

    private TWSDLExtensible parent;
    private ExtensibilityHelper _helper;
    private Documentation _documentation;
    private String _name;
}
