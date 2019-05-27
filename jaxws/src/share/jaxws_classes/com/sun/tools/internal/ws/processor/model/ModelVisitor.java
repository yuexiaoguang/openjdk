package com.sun.tools.internal.ws.processor.model;

public interface ModelVisitor {
    public void visit(Model model) throws Exception;
    public void visit(Service service) throws Exception;
    public void visit(Port port) throws Exception;
    public void visit(Operation operation) throws Exception;
    public void visit(Request request) throws Exception;
    public void visit(Response response) throws Exception;
    public void visit(Fault fault) throws Exception;
    public void visit(Block block) throws Exception;
    public void visit(Parameter parameter) throws Exception;
}
