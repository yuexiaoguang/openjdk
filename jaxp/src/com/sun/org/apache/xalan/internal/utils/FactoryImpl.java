package com.sun.org.apache.xalan.internal.utils;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParserFactory;

public class FactoryImpl {

    static final String DBF = "com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl";
    static final String SF = "com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl";

    static public DocumentBuilderFactory getDOMFactory(boolean useServicesMechanism) {
        DocumentBuilderFactory dbf =
            useServicesMechanism ?
            DocumentBuilderFactory.newInstance() :
            DocumentBuilderFactory.newInstance( DBF,
                FactoryImpl.class.getClassLoader());

        return dbf;
    }
    static public SAXParserFactory getSAXFactory(boolean useServicesMechanism) {
                SAXParserFactory factory =
                    useServicesMechanism ?
                    SAXParserFactory.newInstance() :
                    SAXParserFactory.newInstance(SF,
                        FactoryImpl.class.getClassLoader());
                return factory;
    }
}
