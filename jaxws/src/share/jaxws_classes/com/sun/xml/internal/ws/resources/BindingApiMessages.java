package com.sun.xml.internal.ws.resources;

import com.sun.istack.internal.localization.Localizable;
import com.sun.istack.internal.localization.LocalizableMessageFactory;
import com.sun.istack.internal.localization.Localizer;


/**
 * Defines string formatting method for each constant in the resource file
 */
public final class BindingApiMessages {

    private final static LocalizableMessageFactory messageFactory = new LocalizableMessageFactory("com.sun.xml.internal.ws.resources.bindingApi");
    private final static Localizer localizer = new Localizer();

    public static Localizable localizableBINDING_API_NO_FAULT_MESSAGE_NAME() {
        return messageFactory.getMessage("binding.api.no.fault.message.name");
    }

    /**
     * Fault message name must not be null.
     *
     */
    public static String BINDING_API_NO_FAULT_MESSAGE_NAME() {
        return localizer.localize(localizableBINDING_API_NO_FAULT_MESSAGE_NAME());
    }

}
