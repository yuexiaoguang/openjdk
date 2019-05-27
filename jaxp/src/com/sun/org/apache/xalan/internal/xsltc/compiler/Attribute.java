/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;

final class Attribute extends Instruction {
    private QName _name;

    public void display(int indent) {
        indent(indent);
        Util.println("Attribute " + _name);
        displayContents(indent + IndentIncrement);
    }

    public void parseContents(Parser parser) {
        _name = parser.getQName(getAttribute("name"));
        parseChildren(parser);
        //!!! add text nodes
        //!!! take care of value templates
    }
}
