package com.sun.xml.internal.ws.addressing.model;

import com.sun.xml.internal.ws.resources.AddressingMessages;

import javax.xml.ws.WebServiceException;

public class ActionNotSupportedException extends WebServiceException {
    private String action;

    public ActionNotSupportedException(String action) {
        super(AddressingMessages.ACTION_NOT_SUPPORTED_EXCEPTION(action));
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
