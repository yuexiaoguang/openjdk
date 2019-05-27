package com.sun.xml.internal.rngom.digested;

import com.sun.xml.internal.rngom.ast.builder.Annotations;
import com.sun.xml.internal.rngom.ast.builder.BuildException;
import com.sun.xml.internal.rngom.ast.util.LocatorImpl;

import javax.xml.namespace.QName;

class Annotation implements Annotations<ElementWrapper,LocatorImpl,CommentListImpl> {

    private final DAnnotation a = new DAnnotation();

    public void addAttribute(String ns, String localName, String prefix, String value, LocatorImpl loc) throws BuildException {
        a.attributes.put(new QName(ns,localName,prefix),
            new DAnnotation.Attribute(ns,localName,prefix,value,loc));
    }

    public void addElement(ElementWrapper ea) throws BuildException {
        a.contents.add(ea.element);
    }

    public void addComment(CommentListImpl comments) throws BuildException {
    }

    public void addLeadingComment(CommentListImpl comments) throws BuildException {
    }

    DAnnotation getResult() {
        return a;
    }
}
