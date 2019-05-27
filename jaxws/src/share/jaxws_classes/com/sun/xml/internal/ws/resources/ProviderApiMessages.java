package com.sun.xml.internal.ws.resources;

import com.sun.istack.internal.localization.Localizable;
import com.sun.istack.internal.localization.LocalizableMessageFactory;
import com.sun.istack.internal.localization.Localizer;


/**
 * Defines string formatting method for each constant in the resource file
 */
public final class ProviderApiMessages {

    private final static LocalizableMessageFactory messageFactory = new LocalizableMessageFactory("com.sun.xml.internal.ws.resources.providerApi");
    private final static Localizer localizer = new Localizer();

    public static Localizable localizableNULL_ADDRESS_SERVICE_ENDPOINT() {
        return messageFactory.getMessage("null.address.service.endpoint");
    }

    /**
     * Address in an EPR cannot be null, when serviceName or portName is null
     *
     */
    public static String NULL_ADDRESS_SERVICE_ENDPOINT() {
        return localizer.localize(localizableNULL_ADDRESS_SERVICE_ENDPOINT());
    }

    public static Localizable localizableNO_WSDL_NO_PORT(Object arg0) {
        return messageFactory.getMessage("no.wsdl.no.port", arg0);
    }

    /**
     * WSDL Metadata not available to create the proxy, either Service instance or ServiceEndpointInterface {0} should have WSDL information
     *
     */
    public static String NO_WSDL_NO_PORT(Object arg0) {
        return localizer.localize(localizableNO_WSDL_NO_PORT(arg0));
    }

    public static Localizable localizableNULL_SERVICE() {
        return messageFactory.getMessage("null.service");
    }

    /**
     * serviceName can't be null when portName is specified
     *
     */
    public static String NULL_SERVICE() {
        return localizer.localize(localizableNULL_SERVICE());
    }

    public static Localizable localizableNULL_ADDRESS() {
        return messageFactory.getMessage("null.address");
    }

    /**
     * Address in an EPR cannot be null
     *
     */
    public static String NULL_ADDRESS() {
        return localizer.localize(localizableNULL_ADDRESS());
    }

    public static Localizable localizableNULL_PORTNAME() {
        return messageFactory.getMessage("null.portname");
    }

    /**
     * EPR doesn't have EndpointName in the Metadata
     *
     */
    public static String NULL_PORTNAME() {
        return localizer.localize(localizableNULL_PORTNAME());
    }

    public static Localizable localizableNOTFOUND_SERVICE_IN_WSDL(Object arg0, Object arg1) {
        return messageFactory.getMessage("notfound.service.in.wsdl", arg0, arg1);
    }

    /**
     * Service: {0} not found in WSDL: {1}
     *
     */
    public static String NOTFOUND_SERVICE_IN_WSDL(Object arg0, Object arg1) {
        return localizer.localize(localizableNOTFOUND_SERVICE_IN_WSDL(arg0, arg1));
    }

    public static Localizable localizableNULL_EPR() {
        return messageFactory.getMessage("null.epr");
    }

    /**
     * EndpointReference is null
     *
     */
    public static String NULL_EPR() {
        return localizer.localize(localizableNULL_EPR());
    }

    public static Localizable localizableNULL_WSDL() {
        return messageFactory.getMessage("null.wsdl");
    }

    /**
     * EPR doesn't have WSDL Metadata which is needed for the current operation
     *
     */
    public static String NULL_WSDL() {
        return localizer.localize(localizableNULL_WSDL());
    }

    public static Localizable localizableNOTFOUND_PORT_IN_WSDL(Object arg0, Object arg1, Object arg2) {
        return messageFactory.getMessage("notfound.port.in.wsdl", arg0, arg1, arg2);
    }

    /**
     * Port: {0} not a valid port in Service: {1} in WSDL: {2}
     *
     */
    public static String NOTFOUND_PORT_IN_WSDL(Object arg0, Object arg1, Object arg2) {
        return localizer.localize(localizableNOTFOUND_PORT_IN_WSDL(arg0, arg1, arg2));
    }

    public static Localizable localizableERROR_WSDL(Object arg0) {
        return messageFactory.getMessage("error.wsdl", arg0);
    }

    /**
     * Error in parsing WSDL: {0}
     *
     */
    public static String ERROR_WSDL(Object arg0) {
        return localizer.localize(localizableERROR_WSDL(arg0));
    }

}
