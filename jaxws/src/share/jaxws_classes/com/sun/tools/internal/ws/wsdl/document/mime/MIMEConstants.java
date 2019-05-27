package com.sun.tools.internal.ws.wsdl.document.mime;

import javax.xml.namespace.QName;

/**
 * Interface defining MIME-extension-related constants.
 */
public interface MIMEConstants {

    // namespace URIs
    static final String NS_WSDL_MIME = "http://schemas.xmlsoap.org/wsdl/mime/";

    // QNames
    static final QName QNAME_CONTENT = new QName(NS_WSDL_MIME, "content");
    static final QName QNAME_MULTIPART_RELATED = new QName(NS_WSDL_MIME, "multipartRelated");
    static final QName QNAME_PART = new QName(NS_WSDL_MIME, "part");
    static final QName QNAME_MIME_XML = new QName(NS_WSDL_MIME, "mimeXml");
}
