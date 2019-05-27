package com.sun.tools.internal.ws.wsdl.document;

/**
 * Entity corresponding to the "documentation" WSDL element.
 */
public class Documentation {

    public Documentation(String s) {
        content = s;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String s) {
        content = s;
    }

    public void accept(WSDLDocumentVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    private String content;
}
