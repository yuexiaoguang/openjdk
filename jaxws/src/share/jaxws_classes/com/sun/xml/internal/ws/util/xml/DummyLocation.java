package com.sun.xml.internal.ws.util.xml;

import javax.xml.stream.Location;

/**
 * {@link Location} that returns no info.
 */
public final class DummyLocation implements Location {
    private DummyLocation() {}

    public static final Location INSTANCE = new DummyLocation();

    public int getCharacterOffset() {
        return -1;
    }
    public int getColumnNumber() {
        return -1;
    }
    public int getLineNumber() {
        return -1;
    }
    public String getPublicId() {
        return null;
    }
    public String getSystemId() {
        return null;
    }
}
