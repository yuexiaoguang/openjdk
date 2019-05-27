package com.sun.tools.internal.ws.wsdl.framework;

import com.sun.istack.internal.localization.Localizable;
import com.sun.xml.internal.ws.util.exception.JAXWSExceptionBase;

/**
 * An exception signalling a parsing error.
 */
public class ParseException extends JAXWSExceptionBase {

    public ParseException(String key, Object... args) {
        super(key, args);
    }

    public ParseException(Localizable message){
        super("localized.error", message);
    }

    public ParseException(Throwable throwable) {
        super(throwable);
    }

    public String getDefaultResourceBundleName() {
        return "com.sun.tools.internal.ws.resources.wsdl";
    }
}
