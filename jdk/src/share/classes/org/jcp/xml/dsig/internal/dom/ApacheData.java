package org.jcp.xml.dsig.internal.dom;

import javax.xml.crypto.Data;
import com.sun.org.apache.xml.internal.security.signature.XMLSignatureInput;

/**
 * XMLSignatureInput Data wrapper.
 */
public interface ApacheData extends Data {

    /**
     * Returns the XMLSignatureInput.
     */
    XMLSignatureInput getXMLSignatureInput();
}
