package com.sun.tools.internal.ws.processor.model.jaxb;

/**
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface JAXBTypeVisitor {
    public void visit(JAXBType type) throws Exception;
    public void visit(RpcLitStructure type) throws Exception;
}
