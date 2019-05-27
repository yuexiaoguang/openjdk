package com.sun.xml.internal.ws.api.config.management;

import javax.xml.ws.WebServiceException;

/**
 * Allows to trigger a reconfiguration action on an object.
 */
public interface Reconfigurable {

    /**
     * Executes any action when an endpoint is reconfigured.
     *
     * @throws WebServiceException Thrown if the reconfiguration failed.
     */
    void reconfigure() throws WebServiceException;

}
