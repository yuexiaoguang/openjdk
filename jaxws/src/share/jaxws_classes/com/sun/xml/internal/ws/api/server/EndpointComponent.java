package com.sun.xml.internal.ws.api.server;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import com.sun.xml.internal.ws.api.Component;

/**
 * Interface that allows components around {@link WSEndpoint} to hook up
 * with each other.
 *
 * @deprecated replaced by {@link Component}
 */
public interface EndpointComponent {
    /**
     * Gets the specified SPI.
     *
     * <p>
     * This method works as a kind of directory service
     * for SPIs, allowing various components to define private contract
     * and talk to each other.
     *
     * @return
     *      null if such an SPI is not provided by this object.
     */
    @Nullable <T> T getSPI(@NotNull Class<T> spiType);
}
