package com.sun.xml.internal.ws.api.wsdl.parser;

import com.sun.istack.internal.NotNull;

import javax.xml.transform.Source;
import java.util.List;

/**
 * Abstraction over WSDL and Schema metadata
 */
public abstract class ServiceDescriptor {
    /**
     * Gives list of wsdls
     * @return List of WSDL documents as {@link Source}.
     * {@link javax.xml.transform.Source#getSystemId()} must be Non-null
     */
    public abstract @NotNull List<? extends Source> getWSDLs();

    /**
     * Gives list of schemas.
     * @return List of XML schema documents as {@link Source}. {@link javax.xml.transform.Source#getSystemId()} must be Non-null.
     *
     */
    public abstract @NotNull List<? extends Source> getSchemas();
}
