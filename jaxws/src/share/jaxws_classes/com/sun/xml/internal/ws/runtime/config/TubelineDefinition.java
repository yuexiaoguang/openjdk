package com.sun.xml.internal.ws.runtime.config;

import org.w3c.dom.Element;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>Java class for tubelineDefinitionCType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="tubelineDefinitionCType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="client-side" type="{http://java.sun.com/xml/ns/metro/config}tubeFactoryListCType" minOccurs="0"/>
 *         &lt;element name="endpoint-side" type="{http://java.sun.com/xml/ns/metro/config}tubeFactoryListCType" minOccurs="0"/>
 *         &lt;any/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tubelineDefinitionCType", propOrder = {
    "clientSide",
    "endpointSide",
    "any"
})
public class TubelineDefinition {

    @XmlElement(name = "client-side")
    protected TubeFactoryList clientSide;
    @XmlElement(name = "endpoint-side")
    protected TubeFactoryList endpointSide;
    @XmlAnyElement(lax = true)
    protected List<Object> any;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String name;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the clientSide property.
     *
     * @return
     *     possible object is
     *     {@link TubeFactoryList }
     *
     */
    public TubeFactoryList getClientSide() {
        return clientSide;
    }

    /**
     * Sets the value of the clientSide property.
     *
     * @param value
     *     allowed object is
     *     {@link TubeFactoryList }
     *
     */
    public void setClientSide(TubeFactoryList value) {
        this.clientSide = value;
    }

    /**
     * Gets the value of the endpointSide property.
     *
     * @return
     *     possible object is
     *     {@link TubeFactoryList }
     *
     */
    public TubeFactoryList getEndpointSide() {
        return endpointSide;
    }

    /**
     * Sets the value of the endpointSide property.
     *
     * @param value
     *     allowed object is
     *     {@link TubeFactoryList }
     *
     */
    public void setEndpointSide(TubeFactoryList value) {
        this.endpointSide = value;
    }

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
     * {@link Element }
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

}
