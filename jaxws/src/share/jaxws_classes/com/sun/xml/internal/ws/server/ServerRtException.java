package com.sun.xml.internal.ws.server;

import com.sun.istack.internal.localization.Localizable;
import com.sun.xml.internal.ws.util.exception.JAXWSExceptionBase;

public class ServerRtException extends JAXWSExceptionBase {

    public ServerRtException(String key, Object... args) {
        super(key, args);
    }

    public ServerRtException(Throwable throwable) {
        super(throwable);
    }

    public ServerRtException(Localizable arg) {
        super("server.rt.err", arg);
    }

    public String getDefaultResourceBundleName() {
        return "com.sun.xml.internal.ws.resources.server";
    }

}
