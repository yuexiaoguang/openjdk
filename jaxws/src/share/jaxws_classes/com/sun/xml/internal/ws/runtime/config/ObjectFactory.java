package com.sun.xml.internal.ws.runtime.config;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.sun.xml.internal.ws.runtime.config package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Tubelines_QNAME = new QName("http://java.sun.com/xml/ns/metro/config", "tubelines");
    private final static QName _TubelineMapping_QNAME = new QName("http://java.sun.com/xml/ns/metro/config", "tubeline-mapping");
    private final static QName _Tubeline_QNAME = new QName("http://java.sun.com/xml/ns/metro/config", "tubeline");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sun.xml.internal.ws.runtime.config
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TubeFactoryConfig }
     *
     */
    public TubeFactoryConfig createTubeFactoryConfig() {
        return new TubeFactoryConfig();
    }

    /**
     * Create an instance of {@link TubeFactoryList }
     *
     */
    public TubeFactoryList createTubeFactoryList() {
        return new TubeFactoryList();
    }

    /**
     * Create an instance of {@link TubelineDefinition }
     *
     */
    public TubelineDefinition createTubelineDefinition() {
        return new TubelineDefinition();
    }

    /**
     * Create an instance of {@link Tubelines }
     *
     */
    public Tubelines createTubelines() {
        return new Tubelines();
    }

    /**
     * Create an instance of {@link MetroConfig }
     *
     */
    public MetroConfig createMetroConfig() {
        return new MetroConfig();
    }

    /**
     * Create an instance of {@link TubelineMapping }
     *
     */
    public TubelineMapping createTubelineMapping() {
        return new TubelineMapping();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Tubelines }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://java.sun.com/xml/ns/metro/config", name = "tubelines")
    public JAXBElement<Tubelines> createTubelines(Tubelines value) {
        return new JAXBElement<Tubelines>(_Tubelines_QNAME, Tubelines.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TubelineMapping }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://java.sun.com/xml/ns/metro/config", name = "tubeline-mapping")
    public JAXBElement<TubelineMapping> createTubelineMapping(TubelineMapping value) {
        return new JAXBElement<TubelineMapping>(_TubelineMapping_QNAME, TubelineMapping.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TubelineDefinition }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://java.sun.com/xml/ns/metro/config", name = "tubeline")
    public JAXBElement<TubelineDefinition> createTubeline(TubelineDefinition value) {
        return new JAXBElement<TubelineDefinition>(_Tubeline_QNAME, TubelineDefinition.class, null, value);
    }

}
