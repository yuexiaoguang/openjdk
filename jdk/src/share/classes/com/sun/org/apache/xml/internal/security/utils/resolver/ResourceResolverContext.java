package com.sun.org.apache.xml.internal.security.utils.resolver;

import org.w3c.dom.Attr;

public class ResourceResolverContext {

    public ResourceResolverContext(Attr attr, String baseUri, boolean secureValidation) {
        this.attr = attr;
        this.baseUri = baseUri;
        this.secureValidation = secureValidation;
        this.uriToResolve = attr != null ? attr.getValue() : null;
    }

    public final String uriToResolve;

    public final boolean secureValidation;

    public final String baseUri;

    public final Attr attr;
}
