package com.sun.org.apache.xml.internal.security.encryption;

import java.util.Iterator;
import org.w3c.dom.Element;

/**
 * A wrapper for a pointer from a key value of an <code>EncryptedKey</code> to
 * items encrypted by that key value (<code>EncryptedData</code> or
 * <code>EncryptedKey</code> elements).
 * <p>
 * It is defined as follows:
 * <xmp>
 * <complexType name='ReferenceType'>
 *     <sequence>
 *         <any namespace='##other' minOccurs='0' maxOccurs='unbounded'/>
 *     </sequence>
 *     <attribute name='URI' type='anyURI' use='required'/>
 * </complexType>
 * </xmp>
 */
public interface Reference {
    /**
     * Returns the <code>Element</code> tag name for this <code>Reference</code>.
     *
     * @return the tag name of this <code>Reference</code>.
     */
    String getType();

    /**
     * Returns a <code>URI</code> that points to an <code>Element</code> that
     * were encrypted using the key defined in the enclosing
     * <code>EncryptedKey</code> element.
     *
     * @return an Uniform Resource Identifier that qualifies an
     *   <code>EncryptedType</code>.
     */
    String getURI();

    /**
     * Sets a <code>URI</code> that points to an <code>Element</code> that
     * were encrypted using the key defined in the enclosing
     * <code>EncryptedKey</code> element.
     *
     * @param uri the Uniform Resource Identifier that qualifies an
     *   <code>EncryptedType</code>.
     */
    void setURI(String uri);

    /**
     * Returns an <code>Iterator</code> over all the child elements contained in
     * this <code>Reference</code> that will aid the recipient in retrieving the
     * <code>EncryptedKey</code> and/or <code>EncryptedData</code> elements.
     * These could include information such as XPath transforms, decompression
     * transforms, or information on how to retrieve the elements from a
     * document storage facility.
     *
     * @return child elements.
     */
    Iterator<Element> getElementRetrievalInformation();

    /**
     * Adds retrieval information.
     *
     * @param info
     */
    void addElementRetrievalInformation(Element info);

    /**
     * Removes the specified retrieval information.
     *
     * @param info
     */
    void removeElementRetrievalInformation(Element info);
}
