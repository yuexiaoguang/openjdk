package com.sun.xml.internal.ws.runtime.config;

import org.w3c.dom.Element;

import javax.xml.bind.annotation.*;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>Java class for tubelinesConfigCType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="tubelinesConfigCType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://java.sun.com/xml/ns/metro/config}tubeline-mapping" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://java.sun.com/xml/ns/metro/config}tubeline" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;any/>
 *       &lt;/sequence>
 *       &lt;attribute name="default" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tubelinesConfigCType", propOrder = {
    "tubelineMappings",
    "tubelineDefinitions",
    "any"
})
public class Tubelines {

    @XmlElement(name = "tubeline-mapping")
    protected List<TubelineMapping> tubelineMappings;
    @XmlElement(name = "tubeline")
    protected List<TubelineDefinition> tubelineDefinitions;
    @XmlAnyElement(lax = true)
    protected List<Object> any;
    @XmlAttribute(name = "default")
    @XmlSchemaType(name = "anyURI")
    protected String _default;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the tubelineMappings property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tubelineMappings property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTubelineMappings().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TubelineMapping }
     *
     *
     */
    public List<TubelineMapping> getTubelineMappings() {
        if (tubelineMappings == null) {
            tubelineMappings = new ArrayList<TubelineMapping>();
        }
        return this.tubelineMappings;
    }

    /**
     * Gets the value of the tubelineDefinitions property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tubelineDefinitions property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTubelineDefinitions().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TubelineDefinition }
     *
     *
     */
    public List<TubelineDefinition> getTubelineDefinitions() {
        if (tubelineDefinitions == null) {
            tubelineDefinitions = new ArrayList<TubelineDefinition>();
        }
        return this.tubelineDefinitions;
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
     * Gets the value of the default property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDefault() {
        return _default;
    }

    /**
     * Sets the value of the default property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDefault(String value) {
        this._default = value;
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
