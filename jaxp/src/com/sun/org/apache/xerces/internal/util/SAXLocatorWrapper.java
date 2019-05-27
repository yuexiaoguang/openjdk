package com.sun.org.apache.xerces.internal.util;

import org.xml.sax.Locator;
import org.xml.sax.ext.Locator2;

import com.sun.org.apache.xerces.internal.xni.XMLLocator;

/**
 * <p>A light wrapper around a SAX locator. This is useful
 * when bridging between SAX and XNI components.</p>
 */
public final class SAXLocatorWrapper implements XMLLocator {

    private Locator fLocator = null;
    private Locator2 fLocator2 = null;

    public SAXLocatorWrapper() {}

    public void setLocator(Locator locator) {
        fLocator = locator;
        if (locator instanceof Locator2 || locator == null) {
            fLocator2 = (Locator2) locator;
        }
    }

    public Locator getLocator() {
        return fLocator;
    }

    /*
     * XMLLocator methods
     */

    public String getPublicId() {
        if (fLocator != null) {
            return fLocator.getPublicId();
        }
        return null;
    }

    public String getLiteralSystemId() {
        if (fLocator != null) {
            return fLocator.getSystemId();
        }
        return null;
    }

    public String getBaseSystemId() {
        return null;
    }

    public String getExpandedSystemId() {
        return getLiteralSystemId();
    }

    public int getLineNumber() {
        if (fLocator != null) {
            return fLocator.getLineNumber();
        }
        return -1;
    }

    public int getColumnNumber() {
        if (fLocator != null) {
            return fLocator.getColumnNumber();
        }
        return -1;
    }

    public int getCharacterOffset() {
        return -1;
    }

    public String getEncoding() {
        if (fLocator2 != null) {
            return fLocator2.getEncoding();
        }
        return null;
    }

    public String getXMLVersion() {
        if (fLocator2 != null) {
            return fLocator2.getXMLVersion();
        }
        return null;
    }

} // SAXLocatorWrapper
