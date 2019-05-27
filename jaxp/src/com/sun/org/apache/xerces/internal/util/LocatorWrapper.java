package com.sun.org.apache.xerces.internal.util;

import com.sun.org.apache.xerces.internal.xni.XMLLocator;
import org.xml.sax.Locator;

/**
 * Wraps SAX {@link Locator} into Xerces {@link XMLLocator}.
 */
public class LocatorWrapper implements XMLLocator {

    private final Locator locator;

    public LocatorWrapper( Locator _loc ) { this.locator=_loc; }

    public int getColumnNumber()  { return locator.getColumnNumber(); }
    public int getLineNumber()    { return locator.getLineNumber(); }
    public String getBaseSystemId() { return null; }
    public String getExpandedSystemId() { return locator.getSystemId(); }
    public String getLiteralSystemId() { return locator.getSystemId(); }
    public String getPublicId()   { return locator.getPublicId(); }
    public String getEncoding() { return null; }

    /**
     * <p>Returns the character offset,
     * or <code>-1</code>,
     * if no character offset is available.<p>
     *
     * <p>As this information is not available from
     * {@link org.xml.sax.Locator},
     * always return <code>-1</code>.</p>
     */
    public int getCharacterOffset() {
        return -1;
    }

    /**
     * <p>Returns the XML version of the current entity.</p>
     *
     * <p>As this information is not available from
     * {@link org.xml.sax.Locator},
     * always return <code>null</code>.</p>
     */
    public String getXMLVersion() {
        return null;
    }

}
