package com.sun.xml.internal.ws.spi.db;

import java.util.Map;

import com.oracle.webservices.internal.api.databinding.Databinding;
import com.oracle.webservices.internal.api.databinding.WSDLGenerator;

import com.sun.xml.internal.ws.api.databinding.DatabindingConfig;

public interface DatabindingProvider {
        //We will need this for ServiceFinder
        boolean isFor(String databindingMode);
        void init(Map<String, Object> properties);
        Databinding create(DatabindingConfig config);
    WSDLGenerator wsdlGen(DatabindingConfig config);
}
