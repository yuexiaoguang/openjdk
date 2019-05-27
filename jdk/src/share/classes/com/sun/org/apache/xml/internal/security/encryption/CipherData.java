package com.sun.org.apache.xml.internal.security.encryption;

/**
 * <code>CipherData</code> provides encrypted data. It must either contain the
 * encrypted octet sequence as base64 encoded text of the
 * <code>CipherValue</code> element, or provide a reference to an external
 * location containing the encrypted octet sequence via the
 * <code>CipherReference</code> element.
 * <p>
 * The schema definition is as follows:
 * <xmp>
 * <element name='CipherData' type='xenc:CipherDataType'/>
 * <complexType name='CipherDataType'>
 *     <choice>
 *         <element name='CipherValue' type='base64Binary'/>
 *         <element ref='xenc:CipherReference'/>
 *     </choice>
 * </complexType>
 * </xmp>
 */
public interface CipherData {

    /** VALUE_TYPE ASN */
    int VALUE_TYPE = 0x00000001;

    /** REFERENCE_TYPE ASN */
    int REFERENCE_TYPE = 0x00000002;

    /**
     * Returns the type of encrypted data contained in the
     * <code>CipherData</code>.
     *
     * @return <code>VALUE_TYPE</code> if the encrypted data is contained as
     *   <code>CipherValue</code> or <code>REFERENCE_TYPE</code> if the
     *   encrypted data is contained as <code>CipherReference</code>.
     */
    int getDataType();

    /**
     * Returns the cipher value as a base64 encoded <code>byte</code> array.
     *
     * @return the <code>CipherData</code>'s value.
     */
    CipherValue getCipherValue();

    /**
     * Sets the <code>CipherData</code>'s value.
     *
     * @param value the value of the <code>CipherData</code>.
     * @throws XMLEncryptionException
     */
    void setCipherValue(CipherValue value) throws XMLEncryptionException;

    /**
     * Returns a reference to an external location containing the encrypted
     * octet sequence (<code>byte</code> array).
     *
     * @return the reference to an external location containing the encrypted
     * octet sequence.
     */
    CipherReference getCipherReference();

    /**
     * Sets the <code>CipherData</code>'s reference.
     *
     * @param reference an external location containing the encrypted octet sequence.
     * @throws XMLEncryptionException
     */
    void setCipherReference(CipherReference reference) throws XMLEncryptionException;
}

