package com.sun.tools.internal.ws.resources;

import com.sun.istack.internal.localization.Localizable;
import com.sun.istack.internal.localization.LocalizableMessageFactory;
import com.sun.istack.internal.localization.Localizer;


/**
 * Defines string formatting method for each constant in the resource file
 */
public final class ConfigurationMessages {

    private final static LocalizableMessageFactory messageFactory = new LocalizableMessageFactory("com.sun.tools.internal.ws.resources.configuration");
    private final static Localizer localizer = new Localizer();

    public static Localizable localizableCONFIGURATION_NOT_BINDING_FILE(Object arg0) {
        return messageFactory.getMessage("configuration.notBindingFile", arg0);
    }

    /**
     * Ignoring: binding file ""{0}". It is not a jaxws or a jaxb binding file.
     *
     */
    public static String CONFIGURATION_NOT_BINDING_FILE(Object arg0) {
        return localizer.localize(localizableCONFIGURATION_NOT_BINDING_FILE(arg0));
    }

}
