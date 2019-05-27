package com.sun.org.apache.xml.internal.security.keys.content.keyvalues;

import java.security.PublicKey;

import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;

public interface KeyValueContent {

    /**
     * Method getPublicKey
     *
     * @return the public key
     * @throws XMLSecurityException
     */
    PublicKey getPublicKey() throws XMLSecurityException;

}
