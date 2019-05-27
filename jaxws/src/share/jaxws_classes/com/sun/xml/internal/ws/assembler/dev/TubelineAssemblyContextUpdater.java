package com.sun.xml.internal.ws.assembler.dev;

import javax.xml.ws.WebServiceException;

public interface TubelineAssemblyContextUpdater {
    /**
     * TODO javadoc
     *
     * @param context
     * @throws javax.xml.ws.WebServiceException
     */
    void prepareContext(ClientTubelineAssemblyContext context) throws WebServiceException;

    /**
     * TODO javadoc
     *
     * @param context
     * @throws javax.xml.ws.WebServiceException
     */
    void prepareContext(ServerTubelineAssemblyContext context) throws WebServiceException;
}
