package com.sun.tools.internal.ws.processor.modeler;

import com.sun.tools.internal.ws.processor.model.Model;

/**
 * A Modeler is used to create a Model of a Web Service from a particular Web
 * Web Service description such as a WSDL
*/
public interface Modeler {
    /**
     * Returns the top model of a Web Service. May throw a
     * ModelException if there is a problem with the model.
     *
     * @return Model - the root Node of the model of the Web Service
     *
     * @exception ModelerException
     */
    public Model buildModel();
}
