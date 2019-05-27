package com.sun.xml.internal.ws.api;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import com.sun.xml.internal.ws.api.server.WSEndpoint;

/**
 * Interface that allows components to hook up with each other.
 * Replaces {@link com.sun.xml.internal.ws.api.server.EndpointComponent} so that component
 * pattern can apply to more RI types.
 */
public interface Component {
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
    @Nullable <S> S getSPI(@NotNull Class<S> spiType);
}
