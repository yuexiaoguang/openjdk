package com.sun.xml.internal.stream;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamReader;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;

/**
 * This class wraps XMLInputSource and is also capable of telling wether application
 * returned XMLStreamReader or not when XMLResolver.resolveEnity
 * was called.
 */
public class StaxXMLInputSource {

    XMLStreamReader fStreamReader ;
    XMLEventReader fEventReader ;
    XMLInputSource fInputSource ;

    //indicate if the source is resolved by a resolver
    boolean fHasResolver = false;

    /** Creates a new instance of StaxXMLInputSource */
    public StaxXMLInputSource(XMLStreamReader streamReader) {
        fStreamReader = streamReader ;
    }

    /** Creates a new instance of StaxXMLInputSource */
    public StaxXMLInputSource(XMLEventReader eventReader) {
        fEventReader = eventReader ;
    }

    public StaxXMLInputSource(XMLInputSource inputSource){
        fInputSource = inputSource ;

    }

    public StaxXMLInputSource(XMLInputSource inputSource, boolean hasResolver){
        fInputSource = inputSource ;
        fHasResolver = hasResolver;
    }

    public XMLStreamReader getXMLStreamReader(){
        return fStreamReader ;
    }

    public XMLEventReader getXMLEventReader(){
        return fEventReader ;
    }

    public XMLInputSource getXMLInputSource(){
        return fInputSource ;
    }

    public boolean hasXMLStreamOrXMLEventReader(){
        return (fStreamReader == null) && (fEventReader == null) ? false : true ;
    }

    public boolean hasResolver() {
        return fHasResolver;
    }
}
