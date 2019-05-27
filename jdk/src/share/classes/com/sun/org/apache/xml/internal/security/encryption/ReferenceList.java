package com.sun.org.apache.xml.internal.security.encryption;

import java.util.Iterator;

/**
 * <code>ReferenceList</code> is an element that contains pointers from a key
 * value of an <code>EncryptedKey</code> to items encrypted by that key value
 * (<code>EncryptedData</code> or <code>EncryptedKey</code> elements).
 * <p>
 * It is defined as follows:
 * <xmp>
 * <element name='ReferenceList'>
 *     <complexType>
 *         <choice minOccurs='1' maxOccurs='unbounded'>
 *             <element name='DataReference' type='xenc:ReferenceType'/>
 *             <element name='KeyReference' type='xenc:ReferenceType'/>
 *         </choice>
 *     </complexType>
 * </element>
 * </xmp>
 */
public interface ReferenceList {

    /** DATA TAG */
    int DATA_REFERENCE = 0x00000001;

    /** KEY TAG */
    int KEY_REFERENCE  = 0x00000002;

    /**
     * Adds a reference to this reference list.
     *
     * @param reference the reference to add.
     * @throws IllegalAccessException if the <code>Reference</code> is not an
     *   instance of <code>DataReference</code> or <code>KeyReference</code>.
     */
    void add(Reference reference);

    /**
     * Removes a reference from the <code>ReferenceList</code>.
     *
     * @param reference the reference to remove.
     */
    void remove(Reference reference);

    /**
     * Returns the size of the <code>ReferenceList</code>.
     *
     * @return the size of the <code>ReferenceList</code>.
     */
    int size();

    /**
     * Indicates if the <code>ReferenceList</code> is empty.
     *
     * @return <code><b>true</b></code> if the <code>ReferenceList</code> is
     *     empty, else <code><b>false</b></code>.
     */
    boolean isEmpty();

    /**
     * Returns an <code>Iterator</code> over all the <code>Reference</code>s
     * contained in this <code>ReferenceList</code>.
     *
     * @return Iterator.
     */
    Iterator<Reference> getReferences();

    /**
     * <code>DataReference</code> factory method. Returns a
     * <code>DataReference</code>.
     * @param uri
     * @return a <code>DataReference</code>.
     */
    Reference newDataReference(String uri);

    /**
     * <code>KeyReference</code> factory method. Returns a
     * <code>KeyReference</code>.
     * @param uri
     * @return a <code>KeyReference</code>.
     */
    Reference newKeyReference(String uri);
}
