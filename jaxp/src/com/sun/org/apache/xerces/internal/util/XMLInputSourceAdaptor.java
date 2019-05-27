package com.sun.org.apache.xerces.internal.util;

import javax.xml.transform.Source;

import com.sun.org.apache.xerces.internal.impl.XMLEntityManager;
import com.sun.org.apache.xerces.internal.util.URI.MalformedURIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;

/**
 * {@link Source} that represents an {@link XMLInputSource}.
 *
 * <p>
 * Ideally, we should be able to have {@link XMLInputSource}
 * derive from {@link Source}, but the way
 * the {@link XMLInputSource#getSystemId()} method works is
 * different from the way {@link Source#getSystemId()} method works.
 *
 * <p>
 * In a long run, we should make them consistent so that we can
 * get rid of this awkward adaptor class.
 */
public final class XMLInputSourceAdaptor implements Source {
    /**
     * the actual source information.
     */
    public final XMLInputSource fSource;

    public XMLInputSourceAdaptor( XMLInputSource core ) {
        fSource = core;
    }

    public void setSystemId(String systemId) {
        fSource.setSystemId(systemId);
    }

    public String getSystemId() {
        try {
            return XMLEntityManager.expandSystemId(
                    fSource.getSystemId(), fSource.getBaseSystemId(), false);
        } catch (MalformedURIException e) {
            return fSource.getSystemId();
        }
    }
}
