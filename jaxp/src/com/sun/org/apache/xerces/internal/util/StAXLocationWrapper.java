package com.sun.org.apache.xerces.internal.util;


import javax.xml.stream.Location;

import com.sun.org.apache.xerces.internal.xni.XMLLocator;

/**
 * <p>A light wrapper around a StAX location. This is useful
 * when bridging between StAX and XNI components.</p>
 */
public final class StAXLocationWrapper implements XMLLocator {

    private Location fLocation = null;

    public StAXLocationWrapper() {}

    public void setLocation(Location location) {
        fLocation = location;
    }

    public Location getLocation() {
        return fLocation;
    }

    /*
     * XMLLocator methods
     */

    public String getPublicId() {
        if (fLocation != null) {
            return fLocation.getPublicId();
        }
        return null;
    }

    public String getLiteralSystemId() {
        if (fLocation != null) {
            return fLocation.getSystemId();
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
        if (fLocation != null) {
            return fLocation.getLineNumber();
        }
        return -1;
    }

    public int getColumnNumber() {
        if (fLocation != null) {
            return fLocation.getColumnNumber();
        }
        return -1;
    }

    public int getCharacterOffset() {
        if (fLocation != null) {
            return fLocation.getCharacterOffset();
        }
        return -1;
    }

    public String getEncoding() {
        return null;
    }

    public String getXMLVersion() {
        return null;
    }

} // StAXLocationWrapper
