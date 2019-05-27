package com.sun.xml.internal.bind.v2.runtime;


import javax.activation.DataHandler;
import javax.xml.bind.annotation.adapters.XmlAdapter;


/**
 * Marker class used to identify swaref attachments and to generate appropriate annotations later.
 */
public class SwaRefAdapterMarker extends XmlAdapter<String, DataHandler> {

    public DataHandler unmarshal(String v) throws Exception {
        throw new IllegalStateException("Not implemented");
    }

    public String marshal(DataHandler v) throws Exception {
        throw new IllegalStateException("Not implemented");
    }
}
