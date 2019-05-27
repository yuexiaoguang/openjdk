package com.sun.org.apache.xml.internal.security.encryption;

/**
 * The <code>EncryptedData</code> element is the core element in the syntax. Not
 * only does its <code>CipherData</code> child contain the encrypted data, but
 * it's also the element that replaces the encrypted element, or serves as the
 * new document root.
 * <p>
 * It's schema definition is as follows:
 * <p>
 * <xmp>
 * <element name='EncryptedData' type='xenc:EncryptedDataType'/>
 * <complexType name='EncryptedDataType'>
 *     <complexContent>
 *         <extension base='xenc:EncryptedType'/>
 *     </complexContent>
 * </complexType>
 * </xmp>
 */
public interface EncryptedData extends EncryptedType {
}

