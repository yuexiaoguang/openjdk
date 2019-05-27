package com.sun.xml.internal.ws.resources;

import com.sun.istack.internal.localization.Localizable;
import com.sun.istack.internal.localization.LocalizableMessageFactory;
import com.sun.istack.internal.localization.Localizer;


/**
 * Defines string formatting method for each constant in the resource file
 */
public final class SoapMessages {

    private final static LocalizableMessageFactory messageFactory = new LocalizableMessageFactory("com.sun.xml.internal.ws.resources.soap");
    private final static Localizer localizer = new Localizer();

    public static Localizable localizableSOAP_FAULT_CREATE_ERR(Object arg0) {
        return messageFactory.getMessage("soap.fault.create.err", arg0);
    }

    /**
     * Couldn''t create SOAP Fault due to exception: {0}
     *
     */
    public static String SOAP_FAULT_CREATE_ERR(Object arg0) {
        return localizer.localize(localizableSOAP_FAULT_CREATE_ERR(arg0));
    }

    public static Localizable localizableSOAP_MSG_FACTORY_CREATE_ERR(Object arg0) {
        return messageFactory.getMessage("soap.msg.factory.create.err", arg0);
    }

    /**
     * Couldn''t create SOAP message factory due to exception: {0}
     *
     */
    public static String SOAP_MSG_FACTORY_CREATE_ERR(Object arg0) {
        return localizer.localize(localizableSOAP_MSG_FACTORY_CREATE_ERR(arg0));
    }

    public static Localizable localizableSOAP_MSG_CREATE_ERR(Object arg0) {
        return messageFactory.getMessage("soap.msg.create.err", arg0);
    }

    /**
     * Couldn''t create SOAP message due to exception: {0}
     *
     */
    public static String SOAP_MSG_CREATE_ERR(Object arg0) {
        return localizer.localize(localizableSOAP_MSG_CREATE_ERR(arg0));
    }

    public static Localizable localizableSOAP_FACTORY_CREATE_ERR(Object arg0) {
        return messageFactory.getMessage("soap.factory.create.err", arg0);
    }

    /**
     * Couldn''t create SOAP factory due to exception: {0}
     *
     */
    public static String SOAP_FACTORY_CREATE_ERR(Object arg0) {
        return localizer.localize(localizableSOAP_FACTORY_CREATE_ERR(arg0));
    }

    public static Localizable localizableSOAP_PROTOCOL_INVALID_FAULT_CODE(Object arg0) {
        return messageFactory.getMessage("soap.protocol.invalidFaultCode", arg0);
    }

    /**
     * Invalid fault code: {0}
     *
     */
    public static String SOAP_PROTOCOL_INVALID_FAULT_CODE(Object arg0) {
        return localizer.localize(localizableSOAP_PROTOCOL_INVALID_FAULT_CODE(arg0));
    }

    public static Localizable localizableSOAP_VERSION_MISMATCH_ERR(Object arg0, Object arg1) {
        return messageFactory.getMessage("soap.version.mismatch.err", arg0, arg1);
    }

    /**
     * Couldn''t create SOAP message. Expecting Envelope in namespace {0}, but got {1}
     *
     */
    public static String SOAP_VERSION_MISMATCH_ERR(Object arg0, Object arg1) {
        return localizer.localize(localizableSOAP_VERSION_MISMATCH_ERR(arg0, arg1));
    }

}
