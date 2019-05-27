/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xerces.internal.impl.xs.traversers;

import org.w3c.dom.Element;
import com.sun.org.apache.xerces.internal.impl.xs.opti.ElementImpl;

/**
 * Objects of this class contain the textual representation of
 * an XML schema annotation as well as information on the location
 * of the annotation in the document it originated from.
 */
final class XSAnnotationInfo {

    /** Textual representation of annotation. **/
    String fAnnotation;

    /** Line number of &lt;annotation&gt; element. **/
    int fLine;

    /** Column number of &lt;annotation&gt; element. **/
    int fColumn;

    /** Character offset of &lt;annotation&gt; element. **/
    int fCharOffset;

    /** Next annotation. **/
    XSAnnotationInfo next;

    XSAnnotationInfo(String annotation, int line, int column, int charOffset) {
        fAnnotation = annotation;
        fLine = line;
        fColumn = column;
        fCharOffset = charOffset;
    }

    XSAnnotationInfo(String annotation, Element annotationDecl) {
        fAnnotation = annotation;
        if (annotationDecl instanceof ElementImpl) {
            final ElementImpl annotationDeclImpl = (ElementImpl) annotationDecl;
            fLine = annotationDeclImpl.getLineNumber();
            fColumn = annotationDeclImpl.getColumnNumber();
            fCharOffset = annotationDeclImpl.getCharacterOffset();
        }
        else {
            fLine = -1;
            fColumn = -1;
            fCharOffset = -1;
        }
    }
} // XSAnnotationInfo
