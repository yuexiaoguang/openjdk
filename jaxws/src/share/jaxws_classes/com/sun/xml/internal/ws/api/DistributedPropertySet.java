package com.sun.xml.internal.ws.api;

import com.sun.istack.internal.NotNull;

/**
 * Placeholder for backwards compatibility.
 *
 * @deprecated Use com.oracle.webservices.internal.api.message.DistributedPropertySet instead.
 */
public abstract class DistributedPropertySet extends com.oracle.webservices.internal.api.message.BaseDistributedPropertySet {

    /**
     * @deprecated
     */
    public void addSatellite(@NotNull PropertySet satellite) {
        super.addSatellite(satellite);
    }

    /**
     * @deprecated
     */
    public void addSatellite(@NotNull Class keyClass, @NotNull PropertySet satellite) {
        super.addSatellite(keyClass, satellite);
    }

    /**
     * @deprecated
     */
    public void copySatelliteInto(@NotNull DistributedPropertySet r) {
        super.copySatelliteInto(r);
    }

    /**
     * @deprecated
     */
    public void removeSatellite(PropertySet satellite) {
        super.removeSatellite(satellite);
    }
}
