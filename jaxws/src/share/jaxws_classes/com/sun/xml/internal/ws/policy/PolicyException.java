package com.sun.xml.internal.ws.policy;

/**
 * This is a base exception class and thrown when there is an error in the policy processing
 */
public class PolicyException extends Exception {

    public PolicyException(String message) {
        super(message);
    }


    public PolicyException(String message, Throwable cause) {
        super(message, cause);
    }


    public PolicyException(Throwable cause) {
        super(cause);
    }

}
