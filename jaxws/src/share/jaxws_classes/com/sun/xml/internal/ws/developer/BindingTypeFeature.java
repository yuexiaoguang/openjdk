package com.sun.xml.internal.ws.developer;

import com.sun.xml.internal.ws.api.BindingID;

import javax.xml.ws.WebServiceFeature;

import com.sun.org.glassfish.gmbal.ManagedAttribute;
import com.sun.org.glassfish.gmbal.ManagedData;

/**
 * Using this feature, the application could override the binding used by
 * the runtime(usually determined from WSDL).
 */
@ManagedData
public final class BindingTypeFeature extends WebServiceFeature {

    public static final String ID = "http://jax-ws.dev.java.net/features/binding";

    private final String bindingId;

    public BindingTypeFeature(String bindingId) {
        this.bindingId = bindingId;
    }

    @ManagedAttribute
    public String getID() {
        return ID;
    }

    @ManagedAttribute
    public String getBindingId() {
        return bindingId;
    }

}
