package com.oracle.xmlns.internal.webservices.jaxws_databinding;

import org.w3c.dom.Element;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * This file was generated by JAXB-RI v2.2.6 and afterwards modified
 * to implement appropriate Annotation
 *
 * <p>Java class for java-wsdl-mapping-type complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="java-wsdl-mapping-type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="xml-schema-mapping" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;any maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;group ref="{http://xmlns.oracle.com/webservices/jaxws-databinding}class-annotation" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="java-methods" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://xmlns.oracle.com/webservices/jaxws-databinding}java-method" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="java-type-name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="existing-annotations" type="{http://xmlns.oracle.com/webservices/jaxws-databinding}existing-annotations-type" />
 *       &lt;attribute name="databinding" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;anyAttribute processContents='skip' namespace='##other'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "java-wsdl-mapping-type", propOrder = {
    "xmlSchemaMapping",
    "classAnnotation",
    "javaMethods"
})
public class JavaWsdlMappingType {

    @XmlElement(name = "xml-schema-mapping")
    protected JavaWsdlMappingType.XmlSchemaMapping xmlSchemaMapping;
    @XmlElementRefs({
        @XmlElementRef(name = "web-service-client", namespace = "http://xmlns.oracle.com/webservices/jaxws-databinding", type = XmlWebServiceClient.class, required = false),
        @XmlElementRef(name = "binding-type", namespace = "http://xmlns.oracle.com/webservices/jaxws-databinding", type = XmlBindingType.class, required = false),
        @XmlElementRef(name = "web-service", namespace = "http://xmlns.oracle.com/webservices/jaxws-databinding", type = XmlWebService.class, required = false),
        @XmlElementRef(name = "web-fault", namespace = "http://xmlns.oracle.com/webservices/jaxws-databinding", type = XmlWebFault.class, required = false),
        @XmlElementRef(name = "service-mode", namespace = "http://xmlns.oracle.com/webservices/jaxws-databinding", type = XmlServiceMode.class, required = false),
        @XmlElementRef(name = "mtom", namespace = "http://xmlns.oracle.com/webservices/jaxws-databinding", type = XmlMTOM.class, required = false),
        @XmlElementRef(name = "handler-chain", namespace = "http://xmlns.oracle.com/webservices/jaxws-databinding", type = XmlHandlerChain.class, required = false),
        @XmlElementRef(name = "soap-binding", namespace = "http://xmlns.oracle.com/webservices/jaxws-databinding", type = XmlSOAPBinding.class, required = false)
    })
    @XmlAnyElement
    protected List<Object> classAnnotation;
    @XmlElement(name = "java-methods")
    protected JavaWsdlMappingType.JavaMethods javaMethods;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "java-type-name")
    protected String javaTypeName;
    @XmlAttribute(name = "existing-annotations")
    protected ExistingAnnotationsType existingAnnotations;
    @XmlAttribute(name = "databinding")
    protected String databinding;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the xmlSchemaMapping property.
     *
     * @return
     *     possible object is
     *     {@link JavaWsdlMappingType.XmlSchemaMapping }
     *
     */
    public JavaWsdlMappingType.XmlSchemaMapping getXmlSchemaMapping() {
        return xmlSchemaMapping;
    }

    /**
     * Sets the value of the xmlSchemaMapping property.
     *
     * @param value
     *     allowed object is
     *     {@link JavaWsdlMappingType.XmlSchemaMapping }
     *
     */
    public void setXmlSchemaMapping(JavaWsdlMappingType.XmlSchemaMapping value) {
        this.xmlSchemaMapping = value;
    }

    /**
     *
     *                         The class-annotation group defines the set of
     *                         annotations applicable to the Java class
     *                         declaration.
     *                     Gets the value of the classAnnotation property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the classAnnotation property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClassAnnotation().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XmlWebServiceClient }
     * {@link XmlBindingType }
     * {@link XmlWebService }
     * {@link XmlWebFault }
     * {@link XmlServiceMode }
     * {@link XmlMTOM }
     * {@link XmlHandlerChain }
     * {@link Element }
     * {@link XmlSOAPBinding }
     *
     *
     */
    public List<Object> getClassAnnotation() {
        if (classAnnotation == null) {
            classAnnotation = new ArrayList<Object>();
        }
        return this.classAnnotation;
    }

    /**
     * Gets the value of the javaMethods property.
     *
     * @return
     *     possible object is
     *     {@link JavaWsdlMappingType.JavaMethods }
     *
     */
    public JavaWsdlMappingType.JavaMethods getJavaMethods() {
        return javaMethods;
    }

    /**
     * Sets the value of the javaMethods property.
     *
     * @param value
     *     allowed object is
     *     {@link JavaWsdlMappingType.JavaMethods }
     *
     */
    public void setJavaMethods(JavaWsdlMappingType.JavaMethods value) {
        this.javaMethods = value;
    }

    /**
     * Gets the value of the name property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the javaTypeName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getJavaTypeName() {
        return javaTypeName;
    }

    /**
     * Sets the value of the javaTypeName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setJavaTypeName(String value) {
        this.javaTypeName = value;
    }

    /**
     * Gets the value of the classAnnotations property.
     *
     * @return
     *     possible object is
     *     {@link ExistingAnnotationsType }
     *
     */
    public ExistingAnnotationsType getExistingAnnotations() {
        return existingAnnotations;
    }

    /**
     * Sets the value of the classAnnotations property.
     *
     * @param value
     *     allowed object is
     *     {@link ExistingAnnotationsType }
     *
     */
    public void setExistingAnnotations(ExistingAnnotationsType value) {
        this.existingAnnotations = value;
    }

    /**
     * Gets the value of the databinding property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDatabinding() {
        return databinding;
    }

    /**
     * Sets the value of the databinding property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDatabinding(String value) {
        this.databinding = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     *
     * <p>
     * the map is keyed by the name of the attribute and
     * the value is the string value of the attribute.
     *
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     *
     *
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element ref="{http://xmlns.oracle.com/webservices/jaxws-databinding}java-method" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "javaMethod"
    })
    public static class JavaMethods {

        @XmlElement(name = "java-method")
        protected List<JavaMethod> javaMethod;

        /**
         * Gets the value of the javaMethod property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the javaMethod property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getJavaMethod().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link JavaMethod }
         *
         *
         */
        public List<JavaMethod> getJavaMethod() {
            if (javaMethod == null) {
                javaMethod = new ArrayList<JavaMethod>();
            }
            return this.javaMethod;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;any maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "any"
    })
    public static class XmlSchemaMapping {

        @XmlAnyElement(lax = true)
        protected List<Object> any;

        /**
         * Gets the value of the any property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the any property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAny().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Object }
         *
         *
         */
        public List<Object> getAny() {
            if (any == null) {
                any = new ArrayList<Object>();
            }
            return this.any;
        }

    }

}
