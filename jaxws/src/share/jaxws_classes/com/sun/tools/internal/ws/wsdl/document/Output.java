package com.sun.tools.internal.ws.wsdl.document;

import com.sun.tools.internal.ws.api.wsdl.TWSDLExtensible;
import com.sun.tools.internal.ws.api.wsdl.TWSDLExtension;
import com.sun.tools.internal.ws.wsdl.framework.*;
import com.sun.tools.internal.ws.wscompile.ErrorReceiver;
import com.sun.tools.internal.ws.wscompile.AbortException;
import com.sun.tools.internal.ws.resources.WsdlMessages;
import org.xml.sax.Locator;

import javax.xml.namespace.QName;

/**
 * Entity corresponding to the "output" child element of a port type operation.
 */
public class Output extends Entity implements TWSDLExtensible {

    public Output(Locator locator, ErrorReceiver errReceiver) {
        super(locator);
        this.errorReceiver = errReceiver;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public QName getMessage() {
        return _message;
    }

    public void setMessage(QName n) {
        _message = n;
    }

    public Message resolveMessage(AbstractDocument document) {
        return (Message) document.find(Kinds.MESSAGE, _message);
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

    public void withAllQNamesDo(QNameAction action) {
        if (_message != null) {
            action.perform(_message);
        }
    }

    public void withAllEntityReferencesDo(EntityReferenceAction action) {
        super.withAllEntityReferencesDo(action);
        if (_message != null) {
            action.perform(Kinds.MESSAGE, _message);
        }
    }

    public void accept(WSDLDocumentVisitor visitor) throws Exception {
        visitor.preVisit(this);
        visitor.postVisit(this);
    }

    public void validateThis() {
        if (_message == null) {
            errorReceiver.error(getLocator(), WsdlMessages.VALIDATION_MISSING_REQUIRED_ATTRIBUTE("name", "wsdl:message"));
            throw new AbortException();
        }
    }

    private Documentation _documentation;
    private String _name;
    private QName _message;
    private String _action;
    private ExtensibilityHelper _helper;
    private TWSDLExtensible parent;

    public void addExtension(TWSDLExtension e) {
        _helper.addExtension(e);
    }

    public QName getWSDLElementName() {
        return getElementName();
    }

    public TWSDLExtensible getParent() {
        return parent;
    }

    public void setParent(TWSDLExtensible parent) {
        this.parent = parent;
    }

    public String getNamespaceURI() {
        return getElementName().getNamespaceURI();
    }

    public String getNameValue() {
        return null;
    }

    public Iterable<? extends TWSDLExtension> extensions() {
        return _helper.extensions();
    }

    public String getAction() {
        return _action;
    }

    public void setAction(String _action) {
        this._action = _action;
    }
}
