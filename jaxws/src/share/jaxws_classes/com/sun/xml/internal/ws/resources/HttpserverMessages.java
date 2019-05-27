package com.sun.xml.internal.ws.resources;

import com.sun.istack.internal.localization.Localizable;
import com.sun.istack.internal.localization.LocalizableMessageFactory;
import com.sun.istack.internal.localization.Localizer;


/**
 * Defines string formatting method for each constant in the resource file
 */
public final class HttpserverMessages {

    private final static LocalizableMessageFactory messageFactory = new LocalizableMessageFactory("com.sun.xml.internal.ws.resources.httpserver");
    private final static Localizer localizer = new Localizer();

    public static Localizable localizableUNEXPECTED_HTTP_METHOD(Object arg0) {
        return messageFactory.getMessage("unexpected.http.method", arg0);
    }

    /**
     * Cannot handle HTTP method: {0}
     *
     */
    public static String UNEXPECTED_HTTP_METHOD(Object arg0) {
        return localizer.localize(localizableUNEXPECTED_HTTP_METHOD(arg0));
    }

}
