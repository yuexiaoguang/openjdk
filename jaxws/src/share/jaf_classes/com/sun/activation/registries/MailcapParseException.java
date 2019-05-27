package com.sun.activation.registries;

/**
 *      A class to encapsulate Mailcap parsing related exceptions
 */
public class MailcapParseException extends Exception {

    public MailcapParseException() {
        super();
    }

    public MailcapParseException(String inInfo) {
        super(inInfo);
    }
}
