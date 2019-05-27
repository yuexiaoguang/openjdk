package com.sun.xml.internal.ws.runtime.config;

import org.w3c.dom.Element;

import javax.xml.bind.annotation.*;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>Java class for tubelineMappingCType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="tubelineMappingCType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="endpoint-ref" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;element name="tubeline-ref" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;any/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tubelineMappingCType", propOrder = {
    "endpointRef",
    "tubelineRef",
    "any"
})
public class TubelineMapping {

    @XmlElement(name = "endpoint-ref", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String endpointRef;
    @XmlElement(name = "tubeline-ref", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String tubelineRef;
    @XmlAnyElement(lax = true)
    protected List<Object> any;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the endpointRef property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getEndpointRef() {
        return endpointRef;
    }

    /**
     * Sets the value of the endpointRef property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setEndpointRef(String value) {
        this.endpointRef = value;
    }

    /**
     * Gets the value of the tubelineRef property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTubelineRef() {
        return tubelineRef;
    }

    /**
     * Sets the value of the tubelineRef property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTubelineRef(String value) {
        this.tubelineRef = value;
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
