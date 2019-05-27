package com.sun.tools.internal.ws.wsdl.parser;

import com.sun.tools.internal.ws.api.wsdl.TWSDLExtensible;
import com.sun.tools.internal.ws.api.wsdl.TWSDLExtensionHandler;
import com.sun.tools.internal.ws.api.wsdl.TWSDLParserContext;
import com.sun.tools.internal.ws.util.xml.XmlUtil;
import com.sun.xml.internal.ws.policy.sourcemodel.wspolicy.NamespaceVersion;
import com.sun.xml.internal.ws.policy.sourcemodel.wspolicy.XmlToken;

import org.w3c.dom.Element;

/**
 * Policies are evaluated at runtime. This class makes sure that wscompile/wsimport
 * ignores all policy elements at tooltime.
 */
public class Policy12ExtensionHandler extends TWSDLExtensionHandler {

    @Override
    public String getNamespaceURI() {
        return NamespaceVersion.v1_2.toString();
    }

    @Override
    public boolean handlePortTypeExtension(TWSDLParserContext context, TWSDLExtensible parent, Element e) {
        return handleExtension(context, parent, e);
    }

    @Override
    public boolean handleDefinitionsExtension(TWSDLParserContext context, TWSDLExtensible parent, Element e) {
        return handleExtension(context, parent, e);
    }

    @Override
    public boolean handleBindingExtension(TWSDLParserContext context, TWSDLExtensible parent, Element e) {
        return handleExtension(context, parent, e);
    }

    @Override
    public boolean handleOperationExtension(TWSDLParserContext context, TWSDLExtensible parent, Element e) {
        return handleExtension(context, parent, e);
    }

    @Override
    public boolean handleInputExtension(TWSDLParserContext context, TWSDLExtensible parent, Element e) {
        return handleExtension(context, parent, e);
    }

    @Override
    public boolean handleOutputExtension(TWSDLParserContext context, TWSDLExtensible parent, Element e) {
        return handleExtension(context, parent, e);
    }

    @Override
    public boolean handleFaultExtension(TWSDLParserContext context, TWSDLExtensible parent, Element e) {
        return handleExtension(context, parent, e);
    }

    @Override
    public boolean handleServiceExtension(TWSDLParserContext context, TWSDLExtensible parent, Element e) {
        return handleExtension(context, parent, e);
    }

    @Override
    public boolean handlePortExtension(TWSDLParserContext context, TWSDLExtensible parent, Element e) {
        return handleExtension(context, parent, e);
    }

    /**
     * Only skip the element if it is a <wsp:Policy/>, <wsp:PolicyReference/> or
     * <wsp:UsingPolicy> element.
     */
    private boolean handleExtension(TWSDLParserContext context, TWSDLExtensible parent, Element e) {
        return XmlUtil.matchesTagNS(e, NamespaceVersion.v1_2.asQName(XmlToken.Policy))
               || XmlUtil.matchesTagNS(e,NamespaceVersion.v1_2.asQName(XmlToken.PolicyReference))
               || XmlUtil.matchesTagNS(e, NamespaceVersion.v1_2.asQName(XmlToken.UsingPolicy));
    }

}
