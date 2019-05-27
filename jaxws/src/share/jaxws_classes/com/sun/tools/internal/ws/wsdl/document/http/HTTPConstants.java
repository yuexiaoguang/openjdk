package com.sun.tools.internal.ws.wsdl.document.http;

import javax.xml.namespace.QName;

/**
 * Interface defining HTTP-extension-related constants.
 */
public interface HTTPConstants {

    // namespace URIs
    static final String NS_WSDL_HTTP = "http://schemas.xmlsoap.org/wsdl/http/";

    // QNames
    static final QName QNAME_ADDRESS = new QName(NS_WSDL_HTTP, "address");
    static final QName QNAME_BINDING = new QName(NS_WSDL_HTTP, "binding");
    static final QName QNAME_OPERATION = new QName(NS_WSDL_HTTP, "operation");
    static final QName QNAME_URL_ENCODED = new QName(NS_WSDL_HTTP, "urlEncoded");
    static final QName QNAME_URL_REPLACEMENT = new QName(NS_WSDL_HTTP, "urlReplacement");
}
