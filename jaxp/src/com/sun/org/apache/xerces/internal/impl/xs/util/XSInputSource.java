/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xerces.internal.impl.xs.util;


import com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import com.sun.org.apache.xerces.internal.xs.XSObject;

public final class XSInputSource extends XMLInputSource {

    private SchemaGrammar[] fGrammars;
    private XSObject[] fComponents;

    public XSInputSource(SchemaGrammar[] grammars) {
        super(null, null, null);
        fGrammars = grammars;
        fComponents = null;
    }

    public XSInputSource(XSObject[] component) {
        super(null, null, null);
        fGrammars = null;
        fComponents = component;
    }

    public SchemaGrammar[] getGrammars() {
        return fGrammars;
    }

    public void setGrammars(SchemaGrammar[] grammars) {
        fGrammars = grammars;
    }

    public XSObject[] getComponents() {
        return fComponents;
    }

    public void setComponents(XSObject[] components) {
        fComponents = components;
    }
}
