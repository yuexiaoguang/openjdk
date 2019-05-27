/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xalan.internal.xsltc;

import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;

public interface Translet {

    public void transform(DOM document, SerializationHandler handler)
        throws TransletException;
    public void transform(DOM document, SerializationHandler[] handlers)
        throws TransletException;
    public void transform(DOM document, DTMAxisIterator iterator,
                          SerializationHandler handler)
        throws TransletException;

    public Object addParameter(String name, Object value);

    public void buildKeys(DOM document, DTMAxisIterator iterator,
                          SerializationHandler handler, int root)
        throws TransletException;
    public void addAuxiliaryClass(Class auxClass);
    public Class getAuxiliaryClass(String className);
    public String[] getNamesArray();
    public String[] getUrisArray();
    public int[]    getTypesArray();
    public String[] getNamespaceArray();
    public boolean useServicesMechnism();
    public void setServicesMechnism(boolean flag);

}
