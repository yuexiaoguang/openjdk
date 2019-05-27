package com.sun.xml.internal.ws.wsdl;

import com.sun.istack.internal.Nullable;
import com.sun.xml.internal.ws.api.server.SDDocument;

/**
 * Resolves a systemId to {@link SDDocument}
 */
public interface SDDocumentResolver {
    /**
     * returns {@link SDDocument} for the give systemId.
     *
     * @param systemId document's systemId
     * @return document for the systemId, null if it cannot resolve.
     */
    @Nullable SDDocument resolve(String systemId);

}
