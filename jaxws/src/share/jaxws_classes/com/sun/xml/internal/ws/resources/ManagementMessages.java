package com.sun.xml.internal.ws.resources;

import com.sun.istack.internal.localization.Localizable;
import com.sun.istack.internal.localization.LocalizableMessageFactory;
import com.sun.istack.internal.localization.Localizer;


/**
 * Defines string formatting method for each constant in the resource file
 */
public final class ManagementMessages {

    private final static LocalizableMessageFactory messageFactory = new LocalizableMessageFactory("com.sun.xml.internal.ws.resources.management");
    private final static Localizer localizer = new Localizer();

    public static Localizable localizableWSM_1008_EXPECTED_INTEGER_DISPOSE_DELAY_VALUE(Object arg0) {
        return messageFactory.getMessage("WSM_1008_EXPECTED_INTEGER_DISPOSE_DELAY_VALUE", arg0);
    }

    /**
     * WSM1008: Expected an integer as value of the endpointDisposeDelay attribute, got this instead: "{0}".
     *
     */
    public static String WSM_1008_EXPECTED_INTEGER_DISPOSE_DELAY_VALUE(Object arg0) {
        return localizer.localize(localizableWSM_1008_EXPECTED_INTEGER_DISPOSE_DELAY_VALUE(arg0));
    }

    public static Localizable localizableWSM_1004_EXPECTED_XML_TAG(Object arg0, Object arg1) {
        return messageFactory.getMessage("WSM_1004_EXPECTED_XML_TAG", arg0, arg1);
    }

    /**
     * WSM1004: Expected tag <{0}> but instead read <{1}>.
     *
     */
    public static String WSM_1004_EXPECTED_XML_TAG(Object arg0, Object arg1) {
        return localizer.localize(localizableWSM_1004_EXPECTED_XML_TAG(arg0, arg1));
    }

    public static Localizable localizableWSM_1007_FAILED_MODEL_TRANSLATOR_INSTANTIATION() {
        return messageFactory.getMessage("WSM_1007_FAILED_MODEL_TRANSLATOR_INSTANTIATION");
    }

    /**
     * WSM1007: Failed to create a ModelTranslator instance.
     *
     */
    public static String WSM_1007_FAILED_MODEL_TRANSLATOR_INSTANTIATION() {
        return localizer.localize(localizableWSM_1007_FAILED_MODEL_TRANSLATOR_INSTANTIATION());
    }

    public static Localizable localizableWSM_1002_EXPECTED_MANAGEMENT_ASSERTION(Object arg0) {
        return messageFactory.getMessage("WSM_1002_EXPECTED_MANAGEMENT_ASSERTION", arg0);
    }

    /**
     * WSM1002: Expected policy assertion {0} in this namespace.
     *
     */
    public static String WSM_1002_EXPECTED_MANAGEMENT_ASSERTION(Object arg0) {
        return localizer.localize(localizableWSM_1002_EXPECTED_MANAGEMENT_ASSERTION(arg0));
    }

    public static Localizable localizableWSM_1006_CLIENT_MANAGEMENT_ENABLED() {
        return messageFactory.getMessage("WSM_1006_CLIENT_MANAGEMENT_ENABLED");
    }

    /**
     * WSM1006: The management property of the ManagedClient policy assertion is set to on. Clients cannot be managed and this setting will be ignored.
     *
     */
    public static String WSM_1006_CLIENT_MANAGEMENT_ENABLED() {
        return localizer.localize(localizableWSM_1006_CLIENT_MANAGEMENT_ENABLED());
    }

    public static Localizable localizableWSM_1001_FAILED_ASSERTION(Object arg0) {
        return messageFactory.getMessage("WSM_1001_FAILED_ASSERTION", arg0);
    }

    /**
     * WSM1001: Failed to get policy assertion {0}.
     *
     */
    public static String WSM_1001_FAILED_ASSERTION(Object arg0) {
        return localizer.localize(localizableWSM_1001_FAILED_ASSERTION(arg0));
    }

    public static Localizable localizableWSM_1005_EXPECTED_COMMUNICATION_CHILD() {
        return messageFactory.getMessage("WSM_1005_EXPECTED_COMMUNICATION_CHILD");
    }

    /**
     * WSM1005: Expected to find a CommunicationServerImplementation tag as child node of CommunicationServerImplementations.
     *
     */
    public static String WSM_1005_EXPECTED_COMMUNICATION_CHILD() {
        return localizer.localize(localizableWSM_1005_EXPECTED_COMMUNICATION_CHILD());
    }

    public static Localizable localizableWSM_1003_MANAGEMENT_ASSERTION_MISSING_ID(Object arg0) {
        return messageFactory.getMessage("WSM_1003_MANAGEMENT_ASSERTION_MISSING_ID", arg0);
    }

    /**
     * WSM1003: Policy assertion {0} must have id attribute when management is enabled.
     *
     */
    public static String WSM_1003_MANAGEMENT_ASSERTION_MISSING_ID(Object arg0) {
        return localizer.localize(localizableWSM_1003_MANAGEMENT_ASSERTION_MISSING_ID(arg0));
    }

}
