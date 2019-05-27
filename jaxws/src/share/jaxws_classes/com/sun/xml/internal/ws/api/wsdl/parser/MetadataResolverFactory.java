package com.sun.xml.internal.ws.api.wsdl.parser;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.xml.sax.EntityResolver;

/**
 * Extension point for resolving metadata using wsimport.
 * <p/>
 * wsimport would get a {@link MetaDataResolver} using this factory and from it will resolve all the wsdl/schema
 * metadata.
 *
 * Implementor of this class must provide a zero argument constructor so that
 * it can be loaded during service lookup mechanism.
 */
public abstract class MetadataResolverFactory {
    /**
     * Gets a {@link com.sun.xml.internal.ws.api.wsdl.parser.MetaDataResolver}
     *
     * @param resolver
     */
    public abstract
    @NotNull
    MetaDataResolver metadataResolver(@Nullable EntityResolver resolver);
}
